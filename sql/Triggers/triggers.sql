-- ============================================================
-- TRIGGERS - Sistema de Alquiler de Vehículos
-- ============================================================


-- ============================================================
-- TABLA DE AUDITORÍA (requerida para T1)
-- Guarda un registro histórico de cada cambio de estado
-- en la tabla Alquiler. No se borra ni se modifica, solo
-- se insertan registros nuevos.
-- ============================================================
CREATE TABLE "Auditoria_alquiler" (
    id_auditoria    SERIAL PRIMARY KEY,
    id_alquiler     INTEGER NOT NULL,
    estado_anterior VARCHAR(15),
    estado_nuevo    VARCHAR(15),
    fecha_cambio    TIMESTAMP DEFAULT NOW(),
    usuario_db      VARCHAR(50) DEFAULT CURRENT_USER
);


-- ============================================================
-- T1: trigger_auditoria_alquiler
-- Tipo:      AFTER UPDATE en tabla Alquiler
-- Propósito: Registrar en Auditoria_alquiler cada vez que el
--            estado de un alquiler cambie (ej: Activo → Finalizado,
--            Activo → Cancelado). Permite tener trazabilidad
--            completa de todos los cambios del negocio.
-- Se dispara: Solo cuando el campo 'estado' realmente cambia.
-- ============================================================

-- Función que ejecuta el trigger
CREATE OR REPLACE FUNCTION fn_auditoria_alquiler()
RETURNS TRIGGER AS $$
BEGIN
    -- Solo registrar si el estado realmente cambió
    IF OLD.estado <> NEW.estado THEN
        INSERT INTO "Auditoria_alquiler" (id_alquiler, estado_anterior, estado_nuevo)
        VALUES (OLD.id_alquiler, OLD.estado, NEW.estado);
    END IF;

    -- RETURN NEW es obligatorio en triggers AFTER UPDATE para
    -- confirmar que la fila actualizada se guarda correctamente
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Asociar la función al trigger sobre la tabla Alquiler
CREATE TRIGGER trigger_auditoria_alquiler
    AFTER UPDATE ON "Alquiler"
    FOR EACH ROW
    EXECUTE FUNCTION fn_auditoria_alquiler();


-- ============================================================
-- T2: trigger_validar_disponibilidad_reserva
-- Tipo:      BEFORE INSERT en tabla Reserva
-- Propósito: Antes de crear una reserva, verifica que el
--            vehículo no tenga otra reserva activa (Pendiente
--            o Confirmada) que se solape con las fechas solicitadas.
--            Si hay conflicto, cancela el INSERT con un error claro.
-- Se dispara: Cada vez que se intenta insertar una nueva reserva.
-- ============================================================

-- Función que ejecuta el trigger
CREATE OR REPLACE FUNCTION fn_validar_disponibilidad_reserva()
RETURNS TRIGGER AS $$
DECLARE
    v_conflictos INTEGER;
BEGIN
    -- Contar reservas activas del mismo vehículo que se solapan
    -- con el rango de fechas de la nueva reserva
    SELECT COUNT(*) INTO v_conflictos
    FROM "Reserva"
    WHERE id_vehiculo = NEW.id_vehiculo
      AND estado IN ('Pendiente', 'Confirmada')
      AND (
          -- La nueva reserva empieza dentro de una reserva existente
          NEW.fecha_inicio BETWEEN fecha_inicio AND fecha_fin
          OR
          -- La nueva reserva termina dentro de una reserva existente
          NEW.fecha_fin BETWEEN fecha_inicio AND fecha_fin
          OR
          -- La nueva reserva engloba completamente una reserva existente
          (NEW.fecha_inicio <= fecha_inicio AND NEW.fecha_fin >= fecha_fin)
      );

    -- Si hay al menos un conflicto, cancelar el INSERT con mensaje de error
    IF v_conflictos > 0 THEN
        RAISE EXCEPTION 'El vehículo % ya tiene una reserva activa en las fechas solicitadas (% a %)',
            NEW.id_vehiculo, NEW.fecha_inicio, NEW.fecha_fin;
    END IF;

    -- RETURN NEW en triggers BEFORE INSERT confirma que se permite
    -- continuar con la inserción
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Asociar la función al trigger sobre la tabla Reserva
CREATE TRIGGER trigger_validar_disponibilidad_reserva
    BEFORE INSERT ON "Reserva"
    FOR EACH ROW
    EXECUTE FUNCTION fn_validar_disponibilidad_reserva();


-- ============================================================
-- EJEMPLOS DE USO Y PRUEBAS
-- ============================================================

-- Probar T1: cambiar estado de un alquiler y verificar auditoría
-- UPDATE "Alquiler" SET estado = 'Finalizado' WHERE id_alquiler = 1;
-- SELECT * FROM "Auditoria_alquiler";

-- Probar T2 (caso exitoso): insertar reserva sin conflicto
-- INSERT INTO "Reserva" (id_reserva, fecha_inicio, fecha_fin, estado, id_vehiculo, id_usuario)
-- VALUES (1, '2026-07-01', '2026-07-05', 'Pendiente', 1, 1);

-- Probar T2 (caso error): insertar reserva con fechas solapadas
-- INSERT INTO "Reserva" (id_reserva, fecha_inicio, fecha_fin, estado, id_vehiculo, id_usuario)
-- VALUES (2, '2026-07-03', '2026-07-08', 'Pendiente', 1, 1);
-- Debe lanzar error: "El vehículo 1 ya tiene una reserva activa..."
