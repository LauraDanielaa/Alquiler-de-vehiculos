# Transacciones — Sistema de Alquiler de Vehículos

Este archivo documenta las transacciones implementadas con **PL/pgSQL** para el sistema de alquiler de vehículos. Cada función garantiza **atomicidad**: si algo falla en medio de la operación, todos los cambios se revierten automáticamente.

---

## Tabla de contenido

- [T1 — Registrar un pago](#t1--registrar-un-pago)
  - [¿Qué hace?](#qué-hace)
  - [Parámetros](#parámetros)
  - [Flujo interno](#flujo-interno)
  - [Ejemplos de prueba](#ejemplos-de-prueba)

---

## T1 — Registrar un pago

```sql
SELECT registrar_pago(p_id_alquiler, p_monto, p_metodo_pago);
```

### ¿Qué hace?

Registra un pago para un alquiler de forma **atómica**. Antes de insertar el registro, realiza tres validaciones:

1. Que el alquiler exista en la base de datos.
2. Que el alquiler esté en estado `'Activo'`.
3. Que el monto a pagar no supere el saldo pendiente del alquiler.

Si alguna validación falla, se lanza una excepción y **ningún cambio queda guardado**. Si todo es válido, inserta el pago en la tabla `pago` con estado `'Completado'` y retorna un resumen del resultado.

### Parámetros

| Parámetro       | Tipo           | Descripción                                               |
|-----------------|----------------|-----------------------------------------------------------|
| `p_id_alquiler` | `INTEGER`      | ID del alquiler al que se le aplica el pago               |
| `p_monto`       | `NUMERIC`      | Monto del pago a registrar                                |
| `p_metodo_pago` | `VARCHAR(50)`  | Método de pago: `'Tarjeta'`, `'Efectivo'`, `'Transferencia'` |

**Retorna:** `TEXT` con el ID del pago generado, el monto pagado y el saldo restante.

### Flujo interno

```
registrar_pago(id_alquiler, monto, metodo_pago)
        │
        ├─ ¿Existe el alquiler?          ──✗──► EXCEPTION: No existe
        │
        ├─ ¿Estado = 'Activo'?           ──✗──► EXCEPTION: No está activo
        │
        ├─ calcular_total_alquiler()
        │       └─ total_alquiler
        │
        ├─ SUM(pagos completados)
        │       └─ total_pagado
        │
        ├─ saldo_pendiente = total - pagado
        │
        ├─ ¿monto <= saldo_pendiente?    ──✗──► EXCEPTION: Supera el saldo
        │
        └─ INSERT INTO pago              ──✓──► RETURN mensaje de éxito
```

---

## 🧪 Ejemplos de prueba

Los siguientes casos usan los datos de prueba incluidos en el script de creación.

> **Contexto:** El alquiler `id=10` está en estado `'Activo'` (vehículo Toyota Corolla, del 20 al 25 de mayo de 2026 para el usuario Juan Pérez). Ya tiene dos pagos registrados: uno de `$350.000` (Completado) y otro de `$250.000` (Pendiente — **no** se suma al total pagado). El total del alquiler se calcula con `calcular_total_alquiler(10)`.

---

### Caso 1 — Pago exitoso

Registrar un pago parcial válido para el alquiler activo.

```sql
SELECT registrar_pago(10, 100000, 'Tarjeta');
```

**Resultado esperado:**
```
Pago 11 registrado correctamente por $100000. Saldo restante: $X
```

El nuevo pago queda insertado en la tabla `pago` con estado `'Completado'` y un `id_pago` generado automáticamente por la secuencia.

---

### Caso 2 — Alquiler no existe

Intentar pagar un alquiler con un ID que no existe en la base de datos.

```sql
SELECT registrar_pago(999, 100000, 'Efectivo');
```

**Resultado esperado:**
```
ERROR: No existe un alquiler con id 999
```

Ningún registro es insertado. La transacción se revierte completamente.

---

### Caso 3 — Alquiler no está activo

El alquiler `id=1` tiene estado `'Finalizado'`.

```sql
SELECT registrar_pago(1, 50000, 'Efectivo');
```

**Resultado esperado:**
```
ERROR: El alquiler 1 no está activo (estado actual: Finalizado)
```

La función rechaza el pago antes de consultar montos o tocar la tabla `pago`.

---

### Caso 4 — Monto supera el saldo pendiente

Intentar pagar más de lo que se debe en el alquiler `id=10`.

```sql
SELECT registrar_pago(10, 99999999, 'Transferencia');
```

**Resultado esperado:**
```
ERROR: El monto 99999999 supera el saldo pendiente de $X para el alquiler 10
```

El pago no se registra. La atomicidad garantiza que no queda ningún rastro parcial de la operación.

---

## Tablas involucradas

| Tabla      | Operación   | Descripción                                          |
|------------|-------------|------------------------------------------------------|
| `alquiler` | `SELECT`    | Consulta el estado del alquiler                      |
| `pago`     | `SELECT`    | Suma los pagos completados para calcular lo pagado   |
| `pago`     | `INSERT`    | Registra el nuevo pago si todas las validaciones pasan|

---

## ⚙️ Dependencias

Esta función requiere que exista previamente la función:

```sql
calcular_total_alquiler(p_id_alquiler INTEGER) RETURNS NUMERIC
```

Encargada de calcular el valor total del alquiler (generalmente `precio_dia × días`).


