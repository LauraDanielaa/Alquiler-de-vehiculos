# Vistas

Contiene las vistas que simplifican consultas complejas con múltiples JOINs, usadas directamente desde los endpoints de la API.

---

## vista_detalle_alquileres

Consolida en una sola consulta toda la información relevante de un alquiler: datos del cliente, del vehículo, la categoría, la sucursal y el total de pagos completados. Evita repetir los mismos JOINs en cada endpoint de la API.

**Tablas involucradas:** `alquiler`, `usuario`, `vehiculo`, `categoria_vehiculo`, `sucursal`, `pago`  
**Uso:** `GET /alquileres` y `GET /alquileres/:id`

**Ejemplo**
```sql
-- Ver todos los alquileres con detalle completo
SELECT * FROM vista_detalle_alquileres;

-- Ver un alquiler específico
SELECT * FROM vista_detalle_alquileres WHERE id_alquiler = 1;
-- Resultado esperado: Juan Perez, Honda Civic, Sedan, Bogotá, total_pagado = 450000

-- Filtrar alquileres activos
SELECT * FROM vista_detalle_alquileres WHERE estado_alquiler = 'Activo';

-- Ver alquileres de un cliente específico
SELECT * FROM vista_detalle_alquileres WHERE id_usuario = 1;
```

---

## vista_vehiculos_disponibles

Lista únicamente los vehículos con estado `Disponible` junto con su categoría y sucursal. Se usa en el endpoint de búsqueda de vehículos para alquilar, evitando repetir el JOIN en cada consulta.

**Tablas involucradas:** `vehiculo`, `categoria_vehiculo`, `sucursal`  
**Uso:** `GET /vehiculos/disponibles`

**Ejemplo**
```sql
-- Ver todos los vehículos disponibles
SELECT * FROM vista_vehiculos_disponibles;
-- Resultado esperado: 8 vehículos (excluye el Alquilado y el que está en Mantenimiento)

-- Filtrar por ciudad
SELECT * FROM vista_vehiculos_disponibles WHERE ciudad = 'Bogota';
-- Resultado esperado: Nissan Versa, Honda Civic, Chevrolet Tracker

-- Filtrar por categoría
SELECT * FROM vista_vehiculos_disponibles WHERE categoria = 'SUV';

-- Ordenar por precio
SELECT * FROM vista_vehiculos_disponibles ORDER BY precio_dia ASC;
```
