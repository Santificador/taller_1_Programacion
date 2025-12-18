# 🎉 ¡SWAGGER EJECUTÁNDOSE! 

## 🚀 TU API DE VENTAS ESTÁ CORRIENDO

**Ubicación:** `venta-cafe-api` (Quarkus 3.29.0)

### 📍 URLs de Acceso

#### Swagger UI
```
http://localhost:8080/q/swagger-ui/
```

#### OpenAPI JSON
```
http://localhost:8080/q/openapi
```

#### API REST Endpoints
```
http://localhost:8080/api/ventas
http://localhost:8080/api/ventas/{id}
```

---

## 📋 ENDPOINTS DISPONIBLES EN SWAGGER

### 1️⃣ **POST** - Crear Venta
```
POST /api/ventas
```

**Request Body (Ejemplo):**
```json
{
  "cliente": "María García",
  "items": [
    {
      "producto": "Café Espresso",
      "cantidad": 3,
      "precioUnitario": 75.00
    },
    {
      "producto": "Capuchino",
      "cantidad": 1,
      "precioUnitario": 85.00
    }
  ]
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "cliente": "María García",
  "total": 310.00,
  "fecha": "2025-12-17T20:45:30.123456",
  "items": [
    {
      "producto": "Café Espresso",
      "cantidad": 3,
      "precioUnitario": 75.00
    },
    {
      "producto": "Capuchino",
      "cantidad": 1,
      "precioUnitario": 85.00
    }
  ]
}
```

---

### 2️⃣ **GET** - Listar Todas las Ventas
```
GET /api/ventas
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "cliente": "María García",
    "total": 310.00,
    "fecha": "2025-12-17T20:45:30.123456",
    "items": [...]
  },
  {
    "id": 2,
    "cliente": "Juan Pérez",
    "total": 150.00,
    "fecha": "2025-12-17T20:50:15.654321",
    "items": [...]
  }
]
```

---

### 3️⃣ **GET** - Obtener Venta por ID
```
GET /api/ventas/{id}
```

**Ejemplo:**
```
GET /api/ventas/1
```

**Response (200 OK):**
```json
{
  "id": 1,
  "cliente": "María García",
  "total": 310.00,
  "fecha": "2025-12-17T20:45:30.123456",
  "items": [...]
}
```

**Error (404 Not Found):**
```json
{
  "error": "Venta no encontrada: 999"
}
```

---

### 4️⃣ **PUT** - Actualizar Venta
```
PUT /api/ventas/{id}
```

**Request Body:**
```json
{
  "cliente": "María García - Actualizado",
  "cantidad": 5
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "cliente": "María García - Actualizado",
  "total": 350.00,
  "fecha": "2025-12-17T20:45:30.123456",
  "items": [...]
}
```

---

### 5️⃣ **DELETE** - Eliminar Venta
```
DELETE /api/ventas/{id}
```

**Response (204 No Content):**
```
(sin contenido)
```

---

## ✅ VALIDACIONES EN SWAGGER

### Validación de Request
Cuando envías un POST sin `cliente`:

```json
{
  "items": [...]
}
```

**Response (400 Bad Request):**
```json
{
  "status": 400,
  "error": "Validation failed",
  "message": "Field 'cliente' cannot be null"
}
```

---

## 🧪 CÓMO PROBAR EN SWAGGER

### Paso 1: Abrir Swagger
Ve a: **http://localhost:8080/q/swagger-ui/**

### Paso 2: Ver tu Controller
Busca en la lista: **`VentaRecurso`**

Deberías ver 5 endpoints:
- ✅ POST /api/ventas
- ✅ GET /api/ventas
- ✅ GET /api/ventas/{id}
- ✅ PUT /api/ventas/{id}
- ✅ DELETE /api/ventas/{id}

### Paso 3: Probar un Endpoint

#### Ejemplo: Crear una Venta

1. Click en **`POST /api/ventas`**
2. Click en **`Try it out`**
3. Copia este JSON en el body:
```json
{
  "cliente": "Test Cliente",
  "items": [
    {
      "producto": "Café Test",
      "cantidad": 1,
      "precioUnitario": 50.00
    }
  ]
}
```
4. Click en **`Execute`**
5. ✅ Deberías ver **Response 201 Created**

---

## 📊 ESTRUCTURA DE DATOS

### VentaRequestDTO (Input)
```
- cliente (String, @NotNull) - Nombre del cliente
- items (List<VentaDetalleDTO>, @NotEmpty) - Mínimo 1 item
```

### VentaDetalleDTO
```
- producto (String) - Nombre del producto
- cantidad (int) - Cantidad > 0
- precioUnitario (double) - Precio unitario
```

### VentaResponseDTO (Output)
```
- id (Long) - ID de la venta
- cliente (String) - Nombre del cliente
- total (double) - Total calculado
- fecha (LocalDateTime) - Fecha/hora
- items (List<VentaDetalleDTO>) - Detalles
```

---

## 🔍 VALIDACIONES IMPLEMENTADAS

En **VentaRequestDTO**:
- ✅ **@NotNull cliente** - Campo obligatorio
- ✅ **@NotEmpty items** - Debe tener al menos 1 item

En **Controller**:
- ✅ **@Valid** - Valida automáticamente
- ✅ **Response 400** si falta datos

En **Service**:
- ✅ **@Transactional** - Transacciones automáticas
- ✅ **calcularTotal()** - Suma cantidad × precio
- ✅ **registrarVenta()** - Persiste en BD + Auditoría

---

## 💾 BASE DE DATOS

### Tabla VENTA
```
- id (PK, Long)
- cliente (String)
- cafe (FK)
- cantidad (int)
- precioUnitario (double)
- montoTotal (double)
- fecha (LocalDateTime)
```

### Tabla VENTA_DETALLE
```
- id (PK, Long)
- venta_id (FK)
- producto_id (FK)
- cantidad (int)
- precioUnitario (double)
- subtotal (double)
```

---

## 🎯 FLUJO COMPLETO

```
1. Usuario envía POST /api/ventas con JSON
                    ↓
2. VentaRecurso.crear() recibe
                    ↓
3. @Valid valida automáticamente
                    ↓
4. VentaServicio.registrarVenta() procesa
   ├─ Calcula total
   ├─ Mapea detalles
   └─ Persiste en BD
                    ↓
5. AuditoriaServicio registra automáticamente
                    ↓
6. Response 201 Created con VentaResponseDTO
```

---

## 📸 SCREENSHOT EN SWAGGER

En Swagger verás:

1. **Sección "VentaRecurso"** en verde (POST)
2. **"Try it out"** button
3. **Request body** pre-rellenado
4. **Execute** button
5. **Response 201** con los datos retornados
6. **Response headers** y metadata

---

## ✨ RESUMEN

Tu implementación está:
- ✅ **Corriendo en http://localhost:8080**
- ✅ **Swagger accesible en http://localhost:8080/q/swagger-ui/**
- ✅ **5 endpoints CRUD operativos**
- ✅ **Validaciones funcionando**
- ✅ **BD PostgreSQL conectada**
- ✅ **Auditoría automática registrando**

**¡LISTO PARA PROBAR Y ENTREGAR!** 🎉

---

**Comando para reiniciar si se cierra:**
```bash
java -jar "C:\Users\Pau\OneDrive\Escritorio\Presi\taller_1_entrega_final\venta-cafe-api\target\quarkus-app\quarkus-run.jar"
```

**URL Swagger:**
```
http://localhost:8080/q/swagger-ui/
```
