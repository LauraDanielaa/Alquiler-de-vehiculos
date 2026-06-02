# Triggers

Contiene los triggers que se ejecutan automáticamente ante eventos en la base de datos para garantizar auditoría y validaciones de negocio.

---

## trigger_auditoria_alquiler

Se dispara automáticamente después de cada `UPDATE` en la tabla `alquiler`. Registra en la tabla `auditoria_alquiler` el estado anterior y el nuevo estado cada vez que un alquiler cambia de estado. Permite tener trazabilidad completa de todos los cambios del negocio.

**Tipo:** `AFTER UPDATE`  
**Tabla:** `alquiler`  
**Tabla de auditoría:** `auditoria_alquiler`

**Ejemplo**
```sql
-- Cambiar el estado de un alquiler
UPDATE alquiler SET estado = 'Finalizado' WHERE id_alquiler = 1;

-- Verificar que se registró en la auditoría
SELECT * FROM auditoria_alquiler;
-- Debe aparecer un registro con estado_anterior = 'Activo' y estado_nuevo = 'Finalizado'
```

---

## trigger_validar_disponibilidad_reserva

Se dispara automáticamente antes de cada `INSERT` en la tabla `reserva`. Verifica que el vehículo no tenga otra reserva activa (`Pendiente` o `Confirmada`) que se solape con las fechas de la nueva reserva. Si hay conflicto, cancela la inserción y lanza un error.

**Tipo:** `BEFORE INSERT`  
**Tabla:** `reserva`

**Ejemplo**
```sql
-- Insertar una reserva sin conflicto (caso exitoso)
INSERT INTO reserva (fecha_inicio, fecha_fin, estado, id_vehiculo, id_usuario)
VALUES ('2026-08-01', '2026-08-05', 'Pendiente', 2, 1);
-- Se inserta correctamente

-- Insertar una reserva con fechas solapadas (caso error)
INSERT INTO reserva (fecha_inicio, fecha_fin, estado, id_vehiculo, id_usuario)
VALUES ('2026-08-03', '2026-08-07', 'Pendiente', 2, 2);
-- Error: El vehículo 2 ya tiene una reserva activa en las fechas solicitadas
```
