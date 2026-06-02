-- ============================================================
-- TRANSACCIONES - Sistema de Alquiler de Vehículos
-- ============================================================


-- T1: Registrar un pago
-- Propósito: Garantizar que el registro de un pago sea atómico.
--            Verifica que el alquiler esté activo, que el monto
--            no supere el saldo pendiente y registra el pago.
--            Si cualquier validación falla, se revierte todo.
-- Uso: POST /pagos
CREATE OR REPLACE FUNCTION registrar_pago(
    p_id_alquiler   INTEGER,
    p_monto         NUMERIC,
    p_metodo_pago   VARCHAR(50)
)
RETURNS TEXT AS $$
DECLARE
    v_estado_alquiler   VARCHAR(15);
    v_total_alquiler    NUMERIC;
    v_total_pagado      NUMERIC;
    v_saldo_pendiente   NUMERIC;
    v_nuevo_id          INTEGER;
BEGIN
    -- Iniciar la transacción
    BEGIN
        -- Verificar que el alquiler existe y está activo
        SELECT estado INTO v_estado_alquiler
        FROM alquiler
        WHERE id_alquiler = p_id_alquiler;

        IF NOT FOUND THEN
            RAISE EXCEPTION 'No existe un alquiler con id %', p_id_alquiler;
        END IF;

        IF v_estado_alquiler <> 'Activo' THEN
            RAISE EXCEPTION 'El alquiler % no está activo (estado actual: %)',
                p_id_alquiler, v_estado_alquiler;
        END IF;

        -- Calcular el total que debería pagarse usando el procedimiento ya existente
        v_total_alquiler := calcular_total_alquiler(p_id_alquiler);

        -- Calcular lo que ya se ha pagado
        SELECT COALESCE(SUM(monto), 0) INTO v_total_pagado
        FROM pago
        WHERE id_alquiler = p_id_alquiler
          AND estado = 'Completado';

        -- Calcular el saldo pendiente
        v_saldo_pendiente := v_total_alquiler - v_total_pagado;

        -- Verificar que el nuevo pago no supere el saldo pendiente
        IF p_monto > v_saldo_pendiente THEN
            RAISE EXCEPTION 'El monto % supera el saldo pendiente de % para el alquiler %',
                p_monto, v_saldo_pendiente, p_id_alquiler;
        END IF;

        -- Registrar el pago (id_pago se genera automáticamente por SERIAL)
        INSERT INTO pago (fecha_pago, monto, metodo_pago, estado, id_alquiler)
        VALUES (CURRENT_DATE, p_monto, p_metodo_pago, 'Completado', p_id_alquiler)
        RETURNING id_pago INTO v_nuevo_id;

        -- Todo salió bien, confirmar la transacción
        RETURN 'Pago ' || v_nuevo_id || ' registrado correctamente por $' || p_monto ||
               '. Saldo restante: $' || (v_saldo_pendiente - p_monto);

    EXCEPTION
        -- Si ocurre cualquier error, revertir todos los cambios
        WHEN OTHERS THEN
            RAISE;
    END;
END;
$$ LANGUAGE plpgsql;
