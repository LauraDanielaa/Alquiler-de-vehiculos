-- ============================================================
-- ÍNDICES - Sistema de Alquiler de Vehículos
-- ============================================================


-- I1: Índice sobre estado en alquiler
-- Justificación: La columna estado se consulta frecuentemente para
--               filtrar alquileres activos, finalizados o cancelados
--               desde múltiples endpoints de la API y en los
--               procedimientos almacenados finalizar_alquiler y
--               registrar_alquiler.
CREATE INDEX IF NOT EXISTS idx_alquiler_estado
    ON alquiler (estado);


-- I2: Índice sobre estado en vehiculo
-- Justificación: La columna estado se evalúa en casi todas las
--               consultas de la API para filtrar vehículos disponibles.
--               También es usada por la vista vista_vehiculos_disponibles
--               y el procedimiento registrar_alquiler para verificar
--               disponibilidad antes de crear un alquiler.
CREATE INDEX IF NOT EXISTS idx_vehiculo_estado
    ON vehiculo (estado);


-- I3: Índice compuesto sobre fecha_inicio y fecha_fin en reserva
-- Justificación: El trigger trigger_validar_disponibilidad_reserva
--               realiza búsquedas de solapamiento de fechas en esta
--               tabla en cada INSERT. Sin este índice, esa validación
--               hace un escaneo completo de la tabla en cada reserva nueva.
CREATE INDEX IF NOT EXISTS idx_reserva_fechas
    ON reserva (fecha_inicio, fecha_fin);
