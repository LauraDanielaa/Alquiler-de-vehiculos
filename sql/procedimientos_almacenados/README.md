# Procedimientos Almacenados

Contiene las funciones que encapsulan la lógica de negocio principal del sistema de alquiler de vehículos.

---

## calcular_total_alquiler(p_id_alquiler)

Calcula el costo total de un alquiler multiplicando los días de duración por el precio diario del vehículo, y sumando el monto de todas las multas asociadas al alquiler.

**Parámetros**
| Parámetro | Tipo | Descripción |
|---|---|---|
| p_id_alquiler | INTEGER | ID del alquiler a calcular |

**Retorna:** `NUMERIC` con el monto total a pagar.

**Ejemplo**
```sql
-- Calcular el total del alquiler con id 1
SELECT calcular_total_alquiler(1);

-- Resultado esperado: (días × precio_dia) + suma de multas
-- Ej: 5 días × 90000 + 150000 de multa = 600000
```

---

## registrar_alquiler(p_id_usuario, p_id_vehiculo, p_fecha_inicio, p_fecha_fin)

Crea un nuevo alquiler verificando que el vehículo esté disponible y que el usuario esté registrado como cliente. Si todo es correcto, inserta el alquiler y cambia el estado del vehículo a `Alquilado`.

**Parámetros**
| Parámetro | Tipo | Descripción |
|---|---|---|
| p_id_usuario | INTEGER | ID del cliente que alquila |
| p_id_vehiculo | INTEGER | ID del vehículo a alquilar |
| p_fecha_inicio | DATE | Fecha de inicio del alquiler |
| p_fecha_fin | DATE | Fecha de fin del alquiler |

**Retorna:** `INTEGER` con el id del alquiler creado.

**Ejemplo**
```sql
-- Registrar un nuevo alquiler exitoso
SELECT registrar_alquiler(1, 3, '2026-07-01', '2026-07-05');
-- Retorna el id del nuevo alquiler

-- Caso de error: vehículo no disponible
SELECT registrar_alquiler(1, 1, '2026-07-01', '2026-07-05');
-- Error: El vehículo 1 no está disponible (estado actual: Alquilado)

-- Caso de error: usuario no es cliente
SELECT registrar_alquiler(9, 3, '2026-07-01', '2026-07-05');
-- Error: El usuario 9 no está registrado como Cliente
```

---

## finalizar_alquiler(p_id_alquiler)

Cierra un alquiler verificando que esté activo y que no tenga pagos pendientes. Si las validaciones pasan, cambia el estado del alquiler a `Finalizado` y libera el vehículo cambiando su estado a `Disponible`.

**Parámetros**
| Parámetro | Tipo | Descripción |
|---|---|---|
| p_id_alquiler | INTEGER | ID del alquiler a finalizar |

**Retorna:** `TEXT` con mensaje de confirmación.

**Ejemplo**
```sql
-- Finalizar el alquiler con id 10 (debe no tener pagos pendientes)
SELECT finalizar_alquiler(10);
-- Retorna: 'Alquiler 10 finalizado correctamente. Vehículo 1 liberado.'

-- Caso de error: alquiler con pagos pendientes
SELECT finalizar_alquiler(10);
-- Error: El alquiler 10 tiene 1 pago(s) pendiente(s). Debe saldarlos antes de finalizar.
```
