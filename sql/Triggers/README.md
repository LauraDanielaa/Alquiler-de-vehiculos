Triggers

Se realizaron dos triggers:

1. Auditoría de cambios de estado (trigger_auditoria_alquiler)

Registra automáticamente en la tabla auditoria_alquiler cualquier cambio realizado sobre el estado de un alquiler.

Información almacenada
Estado anterior.
Estado nuevo.
Fecha y hora del cambio.
Usuario de la base de datos que realizó la modificación.

Objetivo: Mantener un historial de cambios para auditoría y trazabilidad.

2. Validación de disponibilidad de reservas (trigger_validar_disponibilidad_reserva)

Se ejecuta antes de insertar una nueva reserva.

Funcionalidades
Verifica si existen reservas activas para el mismo vehículo.
Detecta solapamientos entre rangos de fechas.
Impide la creación de reservas conflictivas.

Objetivo: Evitar la doble reserva de un mismo vehículo.
