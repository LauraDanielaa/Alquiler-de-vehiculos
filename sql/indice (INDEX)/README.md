# Índices

Contiene los índices creados sobre columnas de consulta frecuente para mejorar el rendimiento de la base de datos.

---

## idx_alquiler_estado

Índice sobre la columna `estado` de la tabla `alquiler`. Se justifica porque esta columna se filtra constantemente desde la API para listar alquileres activos, finalizados o cancelados, y también es evaluada por los procedimientos `registrar_alquiler` y `finalizar_alquiler`.

**Tabla:** `alquiler`  
**Columna:** `estado`

**Ejemplo**
```sql
-- Consulta que se beneficia del índice
SELECT * FROM alquiler WHERE estado = 'Activo';

-- Verificar que el índice existe
SELECT indexname, tablename FROM pg_indexes WHERE indexname = 'idx_alquiler_estado';
```

---

## idx_vehiculo_estado

Índice sobre la columna `estado` de la tabla `vehiculo`. Se justifica porque esta columna se evalúa en casi todas las consultas de disponibilidad desde la API, en la vista `vista_vehiculos_disponibles` y en el procedimiento `registrar_alquiler` para verificar si un vehículo puede ser alquilado.

**Tabla:** `vehiculo`  
**Columna:** `estado`

**Ejemplo**
```sql
-- Consulta que se beneficia del índice
SELECT * FROM vehiculo WHERE estado = 'Disponible';

-- Verificar que el índice existe
SELECT indexname, tablename FROM pg_indexes WHERE indexname = 'idx_vehiculo_estado';
```

---

## idx_reserva_fechas

Índice compuesto sobre las columnas `fecha_inicio` y `fecha_fin` de la tabla `reserva`. Se justifica porque el trigger `trigger_validar_disponibilidad_reserva` realiza búsquedas de solapamiento de fechas en cada `INSERT`. Sin este índice, esa validación haría un escaneo completo de la tabla por cada nueva reserva.

**Tabla:** `reserva`  
**Columnas:** `fecha_inicio`, `fecha_fin`

**Ejemplo**
```sql
-- Consulta que se beneficia del índice
SELECT * FROM reserva WHERE fecha_inicio >= '2026-06-01' AND fecha_fin <= '2026-06-30';

-- Verificar que el índice existe
SELECT indexname, tablename FROM pg_indexes WHERE indexname = 'idx_reserva_fechas';

-- Verificar los tres índices de una sola vez
SELECT indexname, tablename
FROM pg_indexes
WHERE indexname IN ('idx_alquiler_estado', 'idx_vehiculo_estado', 'idx_reserva_fechas');
```
