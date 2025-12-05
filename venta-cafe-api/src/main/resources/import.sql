-- Datos iniciales para la base de datos de Venta de Café

-- ========== USUARIOS ==========
INSERT INTO Usuario (id, nombre, correo, rol) VALUES (1, 'Juan Pérez', 'juan.perez@tienda.com', 'Administrador');
INSERT INTO Usuario (id, nombre, correo, rol) VALUES (2, 'María González', 'maria.gonzalez@tienda.com', 'Vendedor');
INSERT INTO Usuario (id, nombre, correo, rol) VALUES (3, 'Carlos Ramírez', 'carlos.ramirez@tienda.com', 'Vendedor');
INSERT INTO Usuario (id, nombre, correo, rol) VALUES (4, 'Ana López', 'ana.lopez@tienda.com', 'Supervisor');

-- ========== CLIENTES ==========
INSERT INTO clientes (ID_CLIENTE, NOMBRE, APELLIDO, EMAIL, TELEFONO, DIRECCION) VALUES (1, 'Pedro', 'Martínez', 'pedro.martinez@email.com', '555-0101', 'Calle Principal 123');
INSERT INTO clientes (ID_CLIENTE, NOMBRE, APELLIDO, EMAIL, TELEFONO, DIRECCION) VALUES (2, 'Laura', 'Sánchez', 'laura.sanchez@email.com', '555-0102', 'Avenida Central 456');
INSERT INTO clientes (ID_CLIENTE, NOMBRE, APELLIDO, EMAIL, TELEFONO, DIRECCION) VALUES (3, 'Roberto', 'García', 'roberto.garcia@email.com', '555-0103', 'Boulevard Norte 789');
INSERT INTO clientes (ID_CLIENTE, NOMBRE, APELLIDO, EMAIL, TELEFONO, DIRECCION) VALUES (4, 'Carmen', 'Torres', 'carmen.torres@email.com', '555-0104', 'Calle Sur 321');
INSERT INTO clientes (ID_CLIENTE, NOMBRE, APELLIDO, EMAIL, TELEFONO, DIRECCION) VALUES (5, 'Miguel', 'Fernández', 'miguel.fernandez@email.com', '555-0105', 'Avenida Este 654');
INSERT INTO clientes (ID_CLIENTE, NOMBRE, APELLIDO, EMAIL, TELEFONO, DIRECCION) VALUES (6, 'Isabel', 'Ruiz', 'isabel.ruiz@email.com', '555-0106', 'Plaza Mayor 147');
INSERT INTO clientes (ID_CLIENTE, NOMBRE, APELLIDO, EMAIL, TELEFONO, DIRECCION) VALUES (7, 'Diego', 'Moreno', 'diego.moreno@email.com', '555-0107', 'Paseo Oeste 258');
INSERT INTO clientes (ID_CLIENTE, NOMBRE, APELLIDO, EMAIL, TELEFONO, DIRECCION) VALUES (8, 'Sofía', 'Castro', 'sofia.castro@email.com', '555-0108', 'Calle Libertad 369');

-- ========== CAFÉS ==========
INSERT INTO cafes (ID_CAFE, NOMBRE_CAFE, DESCRIPCION_CAFE, PRECIO_CAFE, ORIGEN_CAFE, TIPO_TOTADO_CAFE) VALUES (1, 'Café Colombiano Premium', 'Café arábica de las montañas de Colombia, sabor suave y aromático', 12.50, 'Colombia', 2);
INSERT INTO cafes (ID_CAFE, NOMBRE_CAFE, DESCRIPCION_CAFE, PRECIO_CAFE, ORIGEN_CAFE, TIPO_TOTADO_CAFE) VALUES (2, 'Espresso Italiano', 'Mezcla italiana tradicional, tostado oscuro, sabor intenso', 15.00, 'Italia', 3);
INSERT INTO cafes (ID_CAFE, NOMBRE_CAFE, DESCRIPCION_CAFE, PRECIO_CAFE, ORIGEN_CAFE, TIPO_TOTADO_CAFE) VALUES (3, 'Café Brasileño Suave', 'Café de Brasil con notas de chocolate y caramelo', 10.00, 'Brasil', 1);
INSERT INTO cafes (ID_CAFE, NOMBRE_CAFE, DESCRIPCION_CAFE, PRECIO_CAFE, ORIGEN_CAFE, TIPO_TOTADO_CAFE) VALUES (4, 'Ethiopian Yirgacheffe', 'Café etíope con notas florales y cítricas', 18.00, 'Etiopía', 2);
INSERT INTO cafes (ID_CAFE, NOMBRE_CAFE, DESCRIPCION_CAFE, PRECIO_CAFE, ORIGEN_CAFE, TIPO_TOTADO_CAFE) VALUES (5, 'Costa Rica Tarrazú', 'Café de altura con cuerpo medio y acidez brillante', 14.50, 'Costa Rica', 2);
INSERT INTO cafes (ID_CAFE, NOMBRE_CAFE, DESCRIPCION_CAFE, PRECIO_CAFE, ORIGEN_CAFE, TIPO_TOTADO_CAFE) VALUES (6, 'Guatemala Antigua', 'Café con cuerpo completo y sabor a chocolate', 13.00, 'Guatemala', 2);
INSERT INTO cafes (ID_CAFE, NOMBRE_CAFE, DESCRIPCION_CAFE, PRECIO_CAFE, ORIGEN_CAFE, TIPO_TOTADO_CAFE) VALUES (7, 'Sumatra Mandheling', 'Café indonesio con bajo nivel de acidez y notas terrosas', 16.00, 'Indonesia', 3);
INSERT INTO cafes (ID_CAFE, NOMBRE_CAFE, DESCRIPCION_CAFE, PRECIO_CAFE, ORIGEN_CAFE, TIPO_TOTADO_CAFE) VALUES (8, 'Café Descafeinado', 'Café descafeinado de Colombia, proceso Swiss Water', 11.00, 'Colombia', 1);

-- ========== VENTAS ==========
INSERT INTO ventas (id, ID_CLIENTE, ID_CAFE, CANTIDAD_VENTAS, PRECIO_UNITARIO_VENTAS, MONTO_TOTAL_VENTAS, FECHA_VENTAS, CREADO_POR, FECHA_CREACION) 
VALUES (1, 1, 1, 2, 12.50, 25.00, '2025-11-01', 'María González', '2025-11-01 10:30:00');

INSERT INTO ventas (id, ID_CLIENTE, ID_CAFE, CANTIDAD_VENTAS, PRECIO_UNITARIO_VENTAS, MONTO_TOTAL_VENTAS, FECHA_VENTAS, CREADO_POR, FECHA_CREACION) 
VALUES (2, 2, 2, 1, 15.00, 15.00, '2025-11-01', 'María González', '2025-11-01 11:15:00');

INSERT INTO ventas (id, ID_CLIENTE, ID_CAFE, CANTIDAD_VENTAS, PRECIO_UNITARIO_VENTAS, MONTO_TOTAL_VENTAS, FECHA_VENTAS, CREADO_POR, FECHA_CREACION) 
VALUES (3, 3, 4, 3, 18.00, 54.00, '2025-11-02', 'Carlos Ramírez', '2025-11-02 09:45:00');

INSERT INTO ventas (id, ID_CLIENTE, ID_CAFE, CANTIDAD_VENTAS, PRECIO_UNITARIO_VENTAS, MONTO_TOTAL_VENTAS, FECHA_VENTAS, CREADO_POR, FECHA_CREACION) 
VALUES (4, 4, 3, 2, 10.00, 20.00, '2025-11-02', 'María González', '2025-11-02 14:20:00');

INSERT INTO ventas (id, ID_CLIENTE, ID_CAFE, CANTIDAD_VENTAS, PRECIO_UNITARIO_VENTAS, MONTO_TOTAL_VENTAS, FECHA_VENTAS, CREADO_POR, FECHA_CREACION) 
VALUES (5, 5, 5, 1, 14.50, 14.50, '2025-11-03', 'Carlos Ramírez', '2025-11-03 10:00:00');

INSERT INTO ventas (id, ID_CLIENTE, ID_CAFE, CANTIDAD_VENTAS, PRECIO_UNITARIO_VENTAS, MONTO_TOTAL_VENTAS, FECHA_VENTAS, CREADO_POR, FECHA_CREACION) 
VALUES (6, 1, 6, 2, 13.00, 26.00, '2025-11-03', 'María González', '2025-11-03 15:30:00');

INSERT INTO ventas (id, ID_CLIENTE, ID_CAFE, CANTIDAD_VENTAS, PRECIO_UNITARIO_VENTAS, MONTO_TOTAL_VENTAS, FECHA_VENTAS, CREADO_POR, FECHA_CREACION) 
VALUES (7, 6, 7, 1, 16.00, 16.00, '2025-11-04', 'Carlos Ramírez', '2025-11-04 11:45:00');

INSERT INTO ventas (id, ID_CLIENTE, ID_CAFE, CANTIDAD_VENTAS, PRECIO_UNITARIO_VENTAS, MONTO_TOTAL_VENTAS, FECHA_VENTAS, CREADO_POR, FECHA_CREACION) 
VALUES (8, 7, 1, 3, 12.50, 37.50, '2025-11-04', 'María González', '2025-11-04 16:20:00');

INSERT INTO ventas (id, ID_CLIENTE, ID_CAFE, CANTIDAD_VENTAS, PRECIO_UNITARIO_VENTAS, MONTO_TOTAL_VENTAS, FECHA_VENTAS, CREADO_POR, FECHA_CREACION) 
VALUES (9, 8, 8, 2, 11.00, 22.00, '2025-11-05', 'Carlos Ramírez', '2025-11-05 09:10:00');

INSERT INTO ventas (id, ID_CLIENTE, ID_CAFE, CANTIDAD_VENTAS, PRECIO_UNITARIO_VENTAS, MONTO_TOTAL_VENTAS, FECHA_VENTAS, CREADO_POR, FECHA_CREACION) 
VALUES (10, 2, 4, 1, 18.00, 18.00, '2025-11-05', 'María González', '2025-11-05 12:00:00');

-- Reiniciar secuencias
ALTER SEQUENCE usuario_seq RESTART WITH 5;
ALTER SEQUENCE clientes_id_cliente_seq RESTART WITH 9;
ALTER SEQUENCE cafes_id_cafe_seq RESTART WITH 9;
ALTER SEQUENCE ventas_id_seq RESTART WITH 11;