# 🎯 Interfaz Web - Sistema de Venta de Café

## 📋 Descripción

Interfaz HTML interactiva para gestionar ventas de café con sistema completo de auditoría.

## 🚀 Acceso a la Aplicación

### URLs Principales

- **Interfaz Web**: http://localhost:8080/index.html
- **Swagger UI**: http://localhost:8080/q/swagger-ui
- **OpenAPI**: http://localhost:8080/q/openapi

## ✨ Funcionalidades

### 1. 📊 Registrar Venta
- Seleccionar cliente y tipo de café
- Especificar cantidad y fecha
- Cálculo automático del monto total
- Registro de usuario que realiza la venta
- Validación de datos en tiempo real

### 2. 📋 Lista de Ventas
- Ver todas las ventas registradas
- Información completa: cliente, café, cantidad, precios, fechas
- Eliminar ventas con registro de auditoría
- Actualización en tiempo real

### 3. 🔍 Auditoría
- Registro completo de todas las operaciones
- Tipos de acción: CREACION, MODIFICACION, ELIMINACION
- Detalles de usuario, fecha/hora y cambios realizados
- Trazabilidad completa del sistema

### 4. 📈 Estadísticas
- Total de ventas realizadas
- Monto total vendido
- Promedio por venta
- Número de clientes y cafés
- Total de registros de auditoría

## 🎨 Características de la Interfaz

- **Diseño Moderno**: Interfaz con gradientes y efectos visuales
- **Responsiva**: Adaptable a diferentes tamaños de pantalla
- **Intuitiva**: Navegación por pestañas
- **Alertas**: Notificaciones de éxito y error
- **Tiempo Real**: Actualización automática de datos

## 📝 Guía de Uso

### Registrar una Venta

1. Abrir la pestaña "📊 Registrar Venta"
2. Seleccionar un cliente de la lista
3. Seleccionar un tipo de café
4. Ingresar la cantidad deseada
5. Verificar que la fecha sea correcta
6. Ingresar el nombre del usuario que registra
7. El sistema calculará automáticamente el monto total
8. Hacer clic en "💾 Guardar Venta"

### Ver Ventas

1. Abrir la pestaña "📋 Lista de Ventas"
2. Ver la tabla con todas las ventas
3. Usar el botón "🔄 Actualizar" para recargar datos
4. Para eliminar una venta:
   - Hacer clic en "🗑️ Eliminar"
   - Confirmar la acción
   - Ingresar nombre de usuario
   - La eliminación se registrará en auditoría

### Consultar Auditoría

1. Abrir la pestaña "🔍 Auditoría"
2. Ver el registro completo de operaciones
3. Identificar acciones por colores:
   - 🟢 Verde: CREACION
   - 🟡 Amarillo: MODIFICACION
   - 🔴 Rojo: ELIMINACION
4. Ver detalles de cada operación

### Ver Estadísticas

1. Abrir la pestaña "📈 Estadísticas"
2. Ver las métricas del sistema en tarjetas visuales
3. Las estadísticas se actualizan automáticamente

## 🔧 Configuración Técnica

### Archivos Principales

```
venta-cafe-api/src/main/resources/META-INF/resources/
├── index.html    # Interfaz principal
└── app.js        # Lógica de la aplicación
```

### Endpoints de API

```javascript
// Clientes
GET    /api/clientes
GET    /api/clientes/{id}
POST   /api/clientes
PUT    /api/clientes/{id}
DELETE /api/clientes/{id}

// Cafés
GET    /api/cafes
GET    /api/cafes/{id}
POST   /api/cafes
PUT    /api/cafes/{id}
DELETE /api/cafes/{id}

// Ventas
GET    /api/ventas
GET    /api/ventas/{id}
POST   /api/ventas        # Header: X-Usuario
PUT    /api/ventas/{id}   # Header: X-Usuario
DELETE /api/ventas/{id}   # Header: X-Usuario

// Auditoría
GET    /api/auditorias
GET    /api/auditorias/{id}
```

## 🔐 Auditoría Automática

Cada operación de venta registra automáticamente:
- Usuario que realiza la acción
- Fecha y hora exacta
- Tipo de acción (crear, modificar, eliminar)
- Detalles de la operación
- ID del registro afectado

## 🎯 Datos de Prueba

La aplicación incluye datos de prueba:
- **4 Usuarios** (vendedores y administradores)
- **8 Clientes** con información completa
- **8 Tipos de Café** con precios variados
- **10 Ventas** de ejemplo
- Registros de auditoría correspondientes

## 🛠️ Tecnologías Utilizadas

- **Frontend**: HTML5, CSS3, JavaScript (Vanilla)
- **Backend**: Quarkus 3.29.0, Java 17
- **Base de Datos**: PostgreSQL
- **API**: RESTful con JSON
- **Documentación**: OpenAPI 3.0 / Swagger UI

## 📱 Compatibilidad

- ✅ Chrome / Edge (Recomendado)
- ✅ Firefox
- ✅ Safari
- ✅ Dispositivos móviles

## 🐛 Solución de Problemas

### La interfaz no carga
1. Verificar que Quarkus esté ejecutándose
2. Acceder a: http://localhost:8080/index.html
3. Verificar consola del navegador (F12)

### No se muestran datos
1. Verificar que PostgreSQL esté activo
2. Verificar conexión a la base de datos
3. Revisar que los datos iniciales se hayan cargado (import.sql)

### Error de CORS
1. Verificar configuración en application.properties
2. Reiniciar la aplicación Quarkus
3. Limpiar caché del navegador

## 📞 Soporte

Para problemas o sugerencias, revisar:
- Logs de Quarkus en la terminal
- Consola del navegador (F12 > Console)
- Network tab para ver requests (F12 > Network)

---

**Desarrollado con ❤️ para el Sistema de Venta de Café**
