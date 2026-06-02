-- ============================================================
-- VISTAS - Sistema de Alquiler de Vehículos
-- ============================================================


-- V1: Vista detalle de alquileres
-- Propósito: Consolidar en una sola consulta toda la información
--            relevante de un alquiler: cliente, vehículo, categoría,
--            sucursal y total de pagos realizados.
-- Uso: GET /alquileres y GET /alquileres/:id
CREATE OR REPLACE VIEW vista_detalle_alquileres AS
SELECT
    a.id_alquiler,
    a.fecha_inicio,
    a.fecha_fin,
    a.fecha_fin - a.fecha_inicio AS dias,
    a.estado AS estado_alquiler,
    u.id_usuario,
    u.nombre || ' ' || u.apellido AS cliente,
    u.email,
    u.telefono,
    v.id_vehiculo,
    v.placa,
    v.marca,
    v.modelo,
    v."año",
    v.color,
    v.precio_dia,
    c.nombre_categoria AS categoria,
    s.nombre AS sucursal,
    s.ciudad,
    COALESCE(SUM(p.monto) FILTER (WHERE p.estado = 'Completado'), 0) AS total_pagado
FROM "Alquiler" a
JOIN "Usuario" u         ON u.id_usuario   = a.id_usuario
JOIN "Vehiculo" v        ON v.id_vehiculo  = a.id_vehiculo
JOIN "Categoria_vehiculo" c ON c.id_categoria = v.id_categoria
JOIN "Sucursal" s        ON s.id_sucursal  = v.id_sucursal
LEFT JOIN "Pago" p       ON p.id_alquiler  = a.id_alquiler
GROUP BY
    a.id_alquiler, a.fecha_inicio, a.fecha_fin, a.estado,
    u.id_usuario, u.nombre, u.apellido, u.email, u.telefono,
    v.id_vehiculo, v.placa, v.marca, v.modelo, v."año", v.color, v.precio_dia,
    c.nombre_categoria,
    s.nombre, s.ciudad;


-- V2: Vista vehículos disponibles
-- Propósito: Listar únicamente los vehículos disponibles para alquilar
--            junto con su categoría y sucursal, evitando repetir
--            este JOIN en cada consulta de la API.
-- Uso: GET /vehiculos/disponibles
CREATE OR REPLACE VIEW vista_vehiculos_disponibles AS
SELECT
    v.id_vehiculo,
    v.placa,
    v.marca,
    v.modelo,
    v."año",
    v.color,
    v.kilometraje,
    v.precio_dia,
    c.nombre_categoria AS categoria,
    c.descripcion AS descripcion_categoria,
    s.nombre AS sucursal,
    s.ciudad,
    s.telefono AS telefono_sucursal
FROM "Vehiculo" v
JOIN "Categoria_vehiculo" c ON c.id_categoria = v.id_categoria
JOIN "Sucursal" s           ON s.id_sucursal  = v.id_sucursal
WHERE v.estado = 'Disponible'
ORDER BY s.ciudad, c.nombre_categoria, v.precio_dia;
