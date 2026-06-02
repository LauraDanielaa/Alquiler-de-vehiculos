# Subconsultas

Contiene consultas SQL con subconsultas que responden a necesidades específicas del negocio de alquiler de vehículos.

---

## S1 — Vehículos con precio mayor al promedio de su categoría

Usa una subconsulta correlacionada en el `WHERE` para calcular el promedio de `precio_dia` de cada categoría y compararlo con el precio de cada vehículo. Retorna únicamente los vehículos que superan el promedio de su propia categoría.

**Tipo:** Subconsulta correlacionada en `WHERE`  
**Uso:** `GET /vehiculos/premium`

**Ejemplo**
```sql
-- Con las categorías Sedan, SUV y Compacto los promedios son:
-- Sedan:    (120000 + 90000 + 85000) / 3 = 98.333
-- SUV:      (150000 + 180000 + 200000 + 160000) / 4 = 172.500
-- Compacto: (70000 + 65000 + 60000) / 3 = 65.000

-- Resultado esperado:
-- Toyota Corolla  → 120000 (supera promedio Sedan)
-- Honda Civic     → 90000  (supera promedio Sedan)
-- Mazda CX-5      → 180000 (supera promedio SUV)
-- Toyota RAV4     → 200000 (supera promedio SUV)
-- Hyundai Tucson  → 160000 (supera promedio SUV)
-- Renault Sandero → 70000  (supera promedio Compacto)
```

---

## S2 — Clientes que nunca han tenido una multa

Usa una subconsulta anidada con `NOT IN` para identificar los clientes cuyos alquileres no aparecen en la tabla de multas. Útil para programas de fidelización o descuentos a clientes con buen historial.

**Tipo:** Subconsulta anidada en `WHERE` con `NOT IN`  
**Uso:** `GET /clientes/sin-multas`

**Ejemplo**
```sql
-- Con los datos de prueba, las multas están en los alquileres 1, 2 y 9
-- El alquiler 1 y 8 son del usuario 1 (Juan Perez)
-- El alquiler 2 y 9 son del usuario 2 (Maria Lopez)
-- Por lo tanto Juan y Maria quedan excluidos

-- Resultado esperado:
-- Carlos Gomez
-- Ana Martinez
-- Luis Rodriguez
-- Sofia Torres
-- Diego Vargas
```

---

## S3 — Vehículos que nunca han sido alquilados

Usa `NOT EXISTS` para verificar si existe al menos un alquiler asociado a cada vehículo. Si no existe ninguno, el vehículo se incluye en el resultado. Útil para detectar vehículos sin rotación en el inventario.

**Tipo:** Subconsulta en `WHERE` con `NOT EXISTS`  
**Uso:** `GET /vehiculos/sin-alquilar`

**Ejemplo**
```sql
-- Con los datos de prueba, los vehículos que aparecen en algún alquiler son:
-- 1, 2, 3, 4, 5, 7, 8, 9
-- Los que nunca han sido alquilados son el 6 y el 10

-- Resultado esperado:
-- Kia Picanto   → Sucursal Norte, Cali
-- Ford Fiesta   → Sucursal Norte, Cali
```
