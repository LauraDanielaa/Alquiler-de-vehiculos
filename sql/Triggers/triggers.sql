-- ============================================================
-- TRIGGERS - Sistema de Alquiler de Vehículos
-- ============================================================


-- TABLA DE AUDITORÍA (requerida para T1)
-- Guarda un registro histórico de cada cambio de estado
-- en la tabla alquiler. No se borra ni se modifica, solo
-- se insertan registros nuevos.
CREATE TABLE IF NOT EXISTS auditoria_alquiler (
    id_auditoria    SERIAL PRIMARY KEY,
    id_alquiler     INTEGER NOT NULL,
    estado_anterior VARCHAR(15),
    estado_nuevo    VARCHAR(15),
    fecha_cambio    TIMESTAMP DEFAULT NOW(),
    usuario_db      VARCHAR(50) DEFAULT CURRENT_USER
);


-- T1: trigger_auditoria_alquiler
-- Tipo:      AFTER UPDATE en tabla alquiler
-- Propósito: Registrar en auditoria_alquiler cada vez que el
--            estado de un alquiler cambie (ej: Activo → Finalizado,
--            Activo → Cancelado).
-- Se dispara: Solo cuando el campo 'estado' realmente cambia.
CREATE OR REPLACE FUNCTION fn_auditoria_alquiler()
RETURNS TRIGGER AS $$
BEGIN
    -- Solo registrar si el estado realmente cambió
    IF OLD.estado <> NEW.estado THEN
        INSERT INTO auditoria_alquiler (id_alquiler, estado_anterior, estado_nuevo)
        VALUES (OLD.id_alquiler, OLD.estado, NEW.estado);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_auditoria_alquiler
    AFTER UPDATE ON alquiler
    FOR EACH ROW
    EXECUTE FUNCTION fn_auditoria_alquiler();


-- T2: trigger_validar_disponibilidad_reserva
-- Tipo:      BEFORE INSERT en tabla reserva
-- Propósito: Antes de crear una reserva, verifica que el vehículo
--            no tenga otra reserva activa que se solape con las
--            fechas solicitadas. Si hay conflicto, cancela el INSERT.
-- Se dispara: Cada vez que se intenta insertar una nueva reserva.
CREATE OR REPLACE FUNCTION fn_validar_disponibilidad_reserva()
RETURNS TRIGGER AS $$
DECLARE
    v_conflictos INTEGER;
BEGIN
    -- Contar reservas activas del mismo vehículo que se solapan
    -- con el rango de fechas de la nueva reserva
    SELECT COUNT(*) INTO v_conflictos
    FROM reserva
    WHERE id_vehiculo = NEW.id_vehiculo
      AND estado IN ('Pendiente', 'Confirmada')
      AND (
          NEW.fecha_inicio BETWEEN fecha_inicio AND fecha_fin
          OR
          NEW.fecha_fin BETWEEN fecha_inicio AND fecha_fin
          OR
          (NEW.fecha_inicio <= fecha_inicio AND NEW.fecha_fin >= fecha_fin)
      );

    IF v_conflictos > 0 THEN
        RAISE EXCEPTION 'El vehículo % ya tiene una reserva activa en las fechas solicitadas (% a %)',
            NEW.id_vehiculo, NEW.fecha_inicio, NEW.fecha_fin;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_validar_disponibilidad_reserva
    BEFORE INSERT ON reserva
    FOR EACH ROW
    EXECUTE FUNCTION fn_validar_disponibilidad_reserva();
