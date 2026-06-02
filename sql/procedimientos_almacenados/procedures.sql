-- ============================================================
-- PROCEDIMIENTOS ALMACENADOS - Sistema de Alquiler de Vehículos
-- ============================================================


-- P1: calcular_total_alquiler
-- Propósito: Calcula el costo total de un alquiler sumando
--            el costo por días (fecha_fin - fecha_inicio) × precio_dia
--            del vehículo, más la suma de todas las multas asociadas.
-- Retorna:   NUMERIC con el monto total a pagar.
-- Uso:       Llamar antes de registrar o validar un pago.
CREATE OR REPLACE FUNCTION calcular_total_alquiler(p_id_alquiler INTEGER)
RETURNS NUMERIC AS $$
DECLARE
    v_dias          INTEGER;
    v_precio_dia    NUMERIC;
    v_total_multas  NUMERIC;
    v_total         NUMERIC;
BEGIN
    -- Obtener la cantidad de días y el precio diario del vehículo asociado
    SELECT
        (a.fecha_fin - a.fecha_inicio),
        v.precio_dia
    INTO v_dias, v_precio_dia
    FROM alquiler a
    JOIN vehiculo v ON v.id_vehiculo = a.id_vehiculo
    WHERE a.id_alquiler = p_id_alquiler;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'No existe un alquiler con id %', p_id_alquiler;
    END IF;

    -- Sumar el monto de todas las multas del alquiler (0 si no tiene ninguna)
    SELECT COALESCE(SUM(monto), 0)
    INTO v_total_multas
    FROM multa
    WHERE id_alquiler = p_id_alquiler;

    -- Calcular el total: (días × precio por día) + multas
    v_total := (v_dias * v_precio_dia) + v_total_multas;

    RETURN v_total;
END;
$$ LANGUAGE plpgsql;


-- P2: registrar_alquiler
-- Propósito: Encapsula la lógica completa de crear un alquiler:
--            1. Verifica que el vehículo exista y esté 'Disponible'.
--            2. Verifica que el usuario exista como Cliente.
--            3. Inserta el registro en la tabla alquiler.
--            4. Cambia el estado del vehículo a 'Alquilado'.
--            Todo dentro de una transacción para garantizar consistencia.
-- Retorna:   INTEGER con el id_alquiler generado.
-- Uso:       Llamar desde el endpoint POST /alquileres
CREATE OR REPLACE FUNCTION registrar_alquiler(
    p_id_usuario    INTEGER,
    p_id_vehiculo   INTEGER,
    p_fecha_inicio  DATE,
    p_fecha_fin     DATE
)
RETURNS INTEGER AS $$
DECLARE
    v_estado_vehiculo   VARCHAR(15);
    v_nuevo_id          INTEGER;
BEGIN
    IF p_fecha_fin <= p_fecha_inicio THEN
        RAISE EXCEPTION 'La fecha de fin debe ser posterior a la fecha de inicio';
    END IF;

    -- Verificar que el vehículo existe y obtener su estado actual
    SELECT estado INTO v_estado_vehiculo
    FROM vehiculo
    WHERE id_vehiculo = p_id_vehiculo;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'No existe un vehículo con id %', p_id_vehiculo;
    END IF;

    -- Verificar que el vehículo esté disponible para alquilar
    IF v_estado_vehiculo <> 'Disponible' THEN
        RAISE EXCEPTION 'El vehículo % no está disponible (estado actual: %)',
            p_id_vehiculo, v_estado_vehiculo;
    END IF;

    -- Verificar que el usuario existe como Cliente registrado
    IF NOT EXISTS (SELECT 1 FROM cliente WHERE id_usuario = p_id_usuario) THEN
        RAISE EXCEPTION 'El usuario % no está registrado como Cliente', p_id_usuario;
    END IF;

    -- Insertar el nuevo alquiler con estado 'Activo' (SERIAL genera el id automáticamente)
    INSERT INTO alquiler (fecha_inicio, fecha_fin, estado, id_vehiculo, id_usuario)
    VALUES (p_fecha_inicio, p_fecha_fin, 'Activo', p_id_vehiculo, p_id_usuario)
    RETURNING id_alquiler INTO v_nuevo_id;

    -- Cambiar el estado del vehículo a 'Alquilado'
    UPDATE vehiculo
    SET estado = 'Alquilado'
    WHERE id_vehiculo = p_id_vehiculo;

    RETURN v_nuevo_id;

EXCEPTION
    WHEN OTHERS THEN
        RAISE;
END;
$$ LANGUAGE plpgsql;


-- P3: finalizar_alquiler
-- Propósito: Cierra un alquiler de forma segura:
--            1. Verifica que el alquiler exista y esté 'Activo'.
--            2. Verifica que no haya pagos en estado 'Pendiente'.
--            3. Cambia el estado del alquiler a 'Finalizado'.
--            4. Libera el vehículo cambiando su estado a 'Disponible'.
-- Retorna:   TEXT con mensaje de confirmación o error.
-- Uso:       Llamar desde el endpoint PUT /alquileres/:id/finalizar
CREATE OR REPLACE FUNCTION finalizar_alquiler(p_id_alquiler INTEGER)
RETURNS TEXT AS $$
DECLARE
    v_estado_alquiler   VARCHAR(15);
    v_id_vehiculo       INTEGER;
    v_pagos_pendientes  INTEGER;
BEGIN
    -- Obtener el estado actual del alquiler y el vehículo asociado
    SELECT estado, id_vehiculo
    INTO v_estado_alquiler, v_id_vehiculo
    FROM alquiler
    WHERE id_alquiler = p_id_alquiler;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'No existe un alquiler con id %', p_id_alquiler;
    END IF;

    -- Solo se puede finalizar un alquiler que esté activo
    IF v_estado_alquiler <> 'Activo' THEN
        RAISE EXCEPTION 'El alquiler % no está activo (estado actual: %)',
            p_id_alquiler, v_estado_alquiler;
    END IF;

    -- Contar cuántos pagos siguen pendientes para este alquiler
    SELECT COUNT(*) INTO v_pagos_pendientes
    FROM pago
    WHERE id_alquiler = p_id_alquiler AND estado = 'Pendiente';

    -- No se puede finalizar si hay pagos sin completar
    IF v_pagos_pendientes > 0 THEN
        RAISE EXCEPTION 'El alquiler % tiene % pago(s) pendiente(s). Debe saldarlos antes de finalizar.',
            p_id_alquiler, v_pagos_pendientes;
    END IF;

    UPDATE alquiler
    SET estado = 'Finalizado'
    WHERE id_alquiler = p_id_alquiler;

    -- Liberar el vehículo para que pueda ser alquilado nuevamente
    UPDATE vehiculo
    SET estado = 'Disponible'
    WHERE id_vehiculo = v_id_vehiculo;

    RETURN 'Alquiler ' || p_id_alquiler || ' finalizado correctamente. Vehículo ' || v_id_vehiculo || ' liberado.';

EXCEPTION
    WHEN OTHERS THEN
        RAISE;
END;
$$ LANGUAGE plpgsql;
