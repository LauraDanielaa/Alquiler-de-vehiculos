-- ============================================================
-- SUBCONSULTAS - Sistema de Alquiler de Vehículos
-- ============================================================


-- S1: Vehículos con precio mayor al promedio de su categoría
-- Tipo: Subconsulta correlacionada en WHERE
-- Propósito: Identificar vehículos premium dentro de cada categoría
-- Uso: GET /vehiculos/premium
SELECT
    v.id_vehiculo,
    v.placa,
    v.marca,
    v.modelo,
    v."año",
    v.color,
    v.precio_dia,
    c.nombre_categoria
FROM vehiculo v
JOIN categoria_vehiculo c ON c.id_categoria = v.id_categoria
WHERE v.precio_dia > (
    SELECT AVG(v2.precio_dia)
    FROM vehiculo v2
    WHERE v2.id_categoria = v.id_categoria
)
ORDER BY c.nombre_categoria, v.precio_dia DESC;


-- S2: Clientes que nunca han tenido una multa
-- Tipo: Subconsulta anidada en WHERE con NOT IN
-- Propósito: Identificar clientes con buen historial para beneficios o descuentos
-- Uso: GET /clientes/sin-multas
SELECT
    u.id_usuario,
    u.nombre,
    u.apellido,
    u.email,
    u.telefono
FROM usuario u
JOIN cliente cl ON cl.id_usuario = u.id_usuario
WHERE u.id_usuario NOT IN (
    SELECT DISTINCT a.id_usuario
    FROM alquiler a
    WHERE a.id_alquiler IN (
        SELECT DISTINCT m.id_alquiler
        FROM multa m
    )
    AND a.id_usuario IS NOT NULL
)
ORDER BY u.apellido, u.nombre;


-- S3: Vehículos que nunca han sido alquilados
-- Tipo: Subconsulta en WHERE con NOT EXISTS
-- Propósito: Detectar vehículos sin rotación en el inventario
-- Uso: GET /vehiculos/sin-alquilar
SELECT
    v.id_vehiculo,
    v.placa,
    v.marca,
    v.modelo,
    v."año",
    v.color,
    v.precio_dia,
    v.estado,
    s.nombre AS sucursal,
    s.ciudad,
    c.nombre_categoria
FROM vehiculo v
JOIN sucursal s ON s.id_sucursal = v.id_sucursal
JOIN categoria_vehiculo c ON c.id_categoria = v.id_categoria
WHERE NOT EXISTS (
    SELECT 1
    FROM alquiler a
    WHERE a.id_vehiculo = v.id_vehiculo
)
ORDER BY s.ciudad, v.marca;
