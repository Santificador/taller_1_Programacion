# Guía de Auditoría en API de Ventas

## 📋 Resumen

Se ha implementado un sistema completo de auditoría para la entidad **Venta** que registra:
- Quién creó el registro
- Cuándo fue creado
- Quién lo modificó
- Cuándo fue modificado
- Cada operación (CREACION, MODIFICACION, ELIMINACION) se registra en la tabla de auditorías

## 🏗️ Arquitectura

### 1. Enum TipoAccion
```java
public enum TipoAccion {
    CREACION,
    MODIFICACION, 
    ELIMINACION
}
```

### 2. Campos de Auditoría en Venta
- `creadoPor`: String - Usuario que creó
- `fechaCreacion`: LocalDateTime - Cuándo se creó
- `modificadoPor`: String - Usuario que modificó
- `fechaModificacion`: LocalDateTime - Cuándo se modificó

### 3. Entidad Auditoria Mejorada
- `entidad`: Nombre de la entidad auditada
- `operacion`: TipoAccion (enum)
- `usuario`: Quién realizó la acción
- `idRegistro`: ID del registro afectado
- `detalle`: Descripción de los cambios
- `fecha`: Timestamp de la operación

## 📡 Uso de la API

### Crear una Venta
```bash
POST /api/ventas
Headers:
  Content-Type: application/json
  X-Usuario: juan.perez

Body:
{
  "cliente": { "id": 1 },
  "cafe": { "id": 5 },
  "cantidad": 3,
  "precioUnitario": 15.50,
  "montoTotal": 46.50,
  "fecha": "2025-11-03"
}
```

**Resultado:**
- La venta se crea con `creadoPor: "juan.perez"` y `fechaCreacion: [timestamp actual]`
- Se crea un registro en auditorías con `operacion: CREACION`

### Actualizar una Venta
```bash
PUT /api/ventas/1
Headers:
  Content-Type: application/json
  X-Usuario: maria.lopez

Body:
{
  "cliente": { "id": 1 },
  "cafe": { "id": 5 },
  "cantidad": 5,
  "precioUnitario": 15.50,
  "montoTotal": 77.50,
  "fecha": "2025-11-03"
}
```

**Resultado:**
- La venta se actualiza con `modificadoPor: "maria.lopez"` y `fechaModificacion: [timestamp actual]`
- Se crea un registro en auditorías con `operacion: MODIFICACION` y el detalle de cambios

### Eliminar una Venta
```bash
DELETE /api/ventas/1
Headers:
  X-Usuario: admin
```

**Resultado:**
- Se crea un registro en auditorías con `operacion: ELIMINACION` antes de eliminar
- La venta se elimina de la base de datos

## 🔍 Consultar Auditorías

```bash
GET /api/auditorias
```

Ejemplo de respuesta:
```json
[
  {
    "id": 1,
    "entidad": "Venta",
    "operacion": "CREACION",
    "usuario": "juan.perez",
    "idRegistro": 1,
    "detalle": "Venta creada: Cliente=1, Cafe=5, Cantidad=3, Total=46.50",
    "fecha": "2025-11-03T10:30:15"
  },
  {
    "id": 2,
    "entidad": "Venta",
    "operacion": "MODIFICACION",
    "usuario": "maria.lopez",
    "idRegistro": 1,
    "detalle": "Venta modificada. Valores anteriores: Cantidad:3, Total:46.50. Nuevos valores: Cantidad=5, Total=77.50",
    "fecha": "2025-11-03T14:20:30"
  }
]
```

## 🎯 Buenas Prácticas Implementadas

### 1. **Separación de Responsabilidades**
- La entidad tiene métodos auxiliares (`registrarCreacion`, `registrarModificacion`)
- El recurso (controller) maneja la lógica de negocio
- La auditoría se registra en transacción con la operación principal

### 2. **Uso de Enums**
- `TipoAccion` asegura valores consistentes
- Evita errores de tipeo ("creacion" vs "CREACION")
- Facilita validaciones y filtros

### 3. **Header X-Usuario**
- Permite identificar al usuario sin modificar el body
- Valor por defecto "sistema" si no se proporciona
- Fácil de integrar con sistemas de autenticación

### 4. **Transaccionalidad**
- Todo se ejecuta en una transacción
- Si falla la auditoría, se revierte la operación principal
- Garantiza consistencia de datos

### 5. **Detalles Descriptivos**
- Cada registro de auditoría incluye información relevante
- Facilita el seguimiento y debugging
- Útil para reportes y análisis

## 🔧 Configuración de Base de Datos

El script de migración se encuentra en:
```
src/main/resources/db/migration/V2__agregar_campos_auditoria.sql
```

Se ejecutará automáticamente al iniciar Quarkus si usas Liquibase o Flyway.

## 📊 Visualización en Swagger

En Swagger UI (`http://localhost:8080/q/swagger-ui`) verás:

1. **Schemas**: Los modelos muestran los campos de auditoría
2. **Endpoints**: Cada operación muestra el parámetro header `X-Usuario`
3. **Responses**: Las respuestas incluyen los campos de auditoría

### Ejemplo de prueba en Swagger:
1. Expande `POST /api/ventas`
2. Click en "Try it out"
3. Agrega el header `X-Usuario` con tu nombre
4. Completa el JSON del body
5. Click en "Execute"
6. Revisa la respuesta - verás `creadoPor` y `fechaCreacion`

## 🚀 Ventajas del Sistema

✅ **Trazabilidad completa**: Sabes quién hizo qué y cuándo
✅ **No invasivo**: Los clientes pueden seguir usando la API normalmente
✅ **Flexible**: Fácil de extender a otras entidades
✅ **Persistente**: Los registros de auditoría permanecen aunque se elimine el dato original
✅ **Estándar**: Usa JPA puro, sin dependencias externas

## 📝 Extender a Otras Entidades

Para agregar auditoría a otras entidades (Cliente, Cafe, etc.):

1. Agregar los mismos campos de auditoría
2. Agregar los métodos `registrarCreacion()` y `registrarModificacion()`
3. En el Recurso, inyectar `AuditoriaRepositorio`
4. Llamar a `Auditoria.crear()` en cada operación
5. Actualizar el script SQL de migración

## 🐳 Docker y PostgreSQL

Los cambios son compatibles con tu setup actual de Docker + PostgreSQL.
No requiere configuración adicional.

---

**Creado por:** Sistema de Auditoría v1.0
**Fecha:** 2025-11-03
