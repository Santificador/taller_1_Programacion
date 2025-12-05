# 📘 Proyecto Final – Desarrollo de APIs con Quarkus

## 👨‍💻 Autor  
**Nombre:** Eduardo Lezcano  
**Curso:** Desarrollo de APIs con Quarkus  
**Fecha:** Octubre 2025  

---

## 1️⃣ Objetivo del Proyecto

Este sistema permite gestionar las operaciones principales de una cafetería:

- Administración de productos (tipos de café)  
- Registro y gestión de clientes  
- Creación y seguimiento de facturas de venta  

---

## 2️⃣ Pasos a Seguir

### 🔹 Paso 1 – Definir el Tema

**Tema elegido:** *Sistema de Ventas de Café*

**Procesos principales:**

1. Gestión de Productos (Café)  
2. Gestión de Clientes  
3. Registro de Ventas (Facturación)  

---

### 🔹 Paso 2 – Diseñar el DER

#### 🧱 Entidades principales

##### 📦 `productos`
- `id_cafe` (PK) – `int(11)`  
- `nombre_cafe` – `VARCHAR(22)`  
- `descripcion_cafe` – `VARCHAR(100)`  
- `precio_cafe` – `DOUBLE`  
- `origen_cafe` – `VARCHAR(22)`  
- `tipo_tostado` – `INT(22)`  

##### 👤 `clientes`
- `id_cliente` (PK) – `int(11)`  
- `nombre` – `VARCHAR(100)`  
- `apellido` – `VARCHAR(100)`  
- `email` – `VARCHAR(100)`  
- `telefono` – `VARCHAR(20)`  
- `direccion` – `VARCHAR(255)`  

##### 🧾 `facturas`
- `id_factura` (PK) – `int(11)`  
- `id_cliente` (FK) – `int(11)`  
- `cantidad_ventas` – `int(11)`  
- `precio_unitario_ventas` – `int(11)`  
- `monto_total` – `DOUBLE`  
- `fecha` – `DATE`  

---

#### 🔗 Relaciones

- `clientes` 1:N `facturas` → Un cliente puede tener muchas facturas  
- `facturas` 1:N `detalles_factura` → Una factura tiene muchos detalles  
- `productos` 1:N `detalles_factura` → Un producto puede estar en muchos detalles  

---

### 🧑‍🏫 Buenas prácticas

- Tablas en **plural**: `usuarios`, `libros`, `reservas`  
- Columnas con prefijo `id_` para claves primarias: `id_usuario`, `id_libro`  
- Campos de auditoría recomendados: `created_at`, `updated_at`  
