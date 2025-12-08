# Componente: Gestión de Ventas

## 📋 Descripción General

El componente **Gestión de Ventas** es un módulo Angular standalone que permite registrar, gestionar y controlar las ventas de productos de café en la tienda. Proporciona una interfaz completa e intuitiva para:

- **Capturar información del cliente**
- **Seleccionar productos disponibles**
- **Especificar cantidades**
- **Calcular totales automáticamente**
- **Registrar y procesar ventas**

---

## 🎯 Características Principales

### 1. **Formulario de Entrada**
- **Campo Cliente**: Entrada de texto con validación (mínimo 3 caracteres)
- **Producto**: Selector desplegable con todos los productos disponibles y sus precios
- **Cantidad**: Campo numérico con validación (1-100 unidades)
- **Notas**: Campo de texto opcional para anotaciones adicionales

### 2. **Resumen de Venta en Tiempo Real**
- Visualización del cliente seleccionado
- Tabla dinámica con productos agregados
- Cálculo automático de subtotales por producto
- **Total General** destacado en un card con fondo de éxito

### 3. **Gestión de Productos**
- Agregar múltiples productos a la misma venta
- Eliminación individual de productos
- Control de cantidades per producto
- Sistema inteligente que agrupa productos duplicados

### 4. **Validaciones**
- Campos requeridos destacados
- Mensajes de error contextualizados
- Botones deshabilitados cuando falta información
- Prevención de ventas vacías

### 5. **Interfaz Visual**
- Diseño responsive con Bootstrap 5
- Iconos Font Awesome
- Agrupación en cards visuales
- Tabla clara y bien estructurada
- Animaciones suaves

---

## 📂 Estructura de Archivos

```
src/main/webapp/app/entities/gestion-ventas/
├── gestion-ventas.component.ts        # Lógica del componente
├── gestion-ventas.component.html      # Template HTML
├── gestion-ventas.component.scss      # Estilos personalizados
├── gestion-ventas.routes.ts           # Configuración de rutas
└── README.md                          # Este archivo
```

---

## 🔧 Configuración e Integración

### 1. **Ruta Registrada**
La ruta ha sido registrada en:
- **Archivo**: `src/main/webapp/app/entities/entity.routes.ts`
- **Ruta**: `/gestion-ventas`
- **Componente**: `GestionVentasComponent`

### 2. **Menú de Navegación**
El enlace se ha agregado automáticamente al navbar con:
- **Nombre visible**: "Gestión de Ventas"
- **Icono**: Shopping Cart (`fa-shopping-cart`)
- **Ubicación**: Dropdown de "Entities" en el navbar

### 3. **Traducción (i18n)**
Las etiquetas están configuradas en:
- **Archivo**: `src/main/webapp/i18n/en/gestionVentas.json`
- **Clave principal**: `ventacafeappApp.gestionVentas`

---

## 🛠️ Modelos de Datos

### Interfaz `ProductoVenta`
```typescript
interface ProductoVenta {
  id: number;              // ID único del producto
  nombre: string;          // Nombre del producto
  cantidad: number;        // Cantidad vendida
  precioUnitario: number;  // Precio por unidad
}
```

### Objeto de Venta (al registrar)
```typescript
{
  cliente: string;                          // Nombre del cliente
  productos: ProductoVenta[];               // Array de productos
  total: number;                            // Total en dinero
  notas: string;                            // Notas opcionales
  fecha: string;                            // ISO timestamp
}
```

---

## 📊 Productos Disponibles (Datos Mockados)

1. **Café Espresso** - $25.00
2. **Café Americano** - $20.00
3. **Café Latte** - $30.00
4. **Cappuccino** - $32.00
5. **Mocca** - $35.00
6. **Café Cortado** - $22.00

> **Nota**: Estos datos están mockados en el componente. Para usar datos reales desde el backend, implementar un servicio que consulte la API.

---

## 🎨 Componentes Utilizados

- **Angular**: Standalone component, Reactive Forms, Router
- **Bootstrap 5**: Grillas, Cards, Botones, Validación
- **Font Awesome**: Iconografía
- **Angular Translate**: Internacionalización (i18n)
- **RxJS**: Gestor de estado reactivo

---

## 🔄 Métodos Principales

### `inicializarFormulario(): void`
Crea el formulario reactivo con validaciones.

### `agregarProducto(): void`
- Valida el formulario
- Busca el producto seleccionado
- Verifica si ya existe en la lista (agrupa cantidades)
- Actualiza el total

### `eliminarProducto(index: number): void`
- Elimina un producto de la lista
- Recalcula el total

### `calcularTotal(): void`
Suma todos los subtotales (cantidad × precio unitario).

### `registrarVenta(): void`
- Valida que haya cliente y al menos un producto
- Crea objeto de venta
- Registra en consola (mockado)
- Muestra confirmación al usuario
- Limpia el formulario

### `limpiarFormulario(): void`
Reinicia todos los campos y productos.

---

## 🎯 Flujo de Uso

1. **Ingrese datos del cliente**
   - Nombre de cliente (mínimo 3 caracteres)

2. **Seleccione un producto**
   - Elija de la lista desplegable

3. **Especifique la cantidad**
   - Entre 1 y 100 unidades

4. **Agregue el producto**
   - Click en "Agregar Producto"
   - El producto aparece en la tabla de resumen

5. **Repita** pasos 2-4 para agregar más productos

6. **Registre la venta**
   - Click en "Registrar Venta"
   - Se muestra confirmación con el total

7. **Inicie nueva venta**
   - El formulario se limpia automáticamente

---

## 📱 Responsive Design

El componente es completamente responsive:
- **Desktop**: Dos columnas (formulario + resumen)
- **Tablet**: Disposición adaptada
- **Mobile**: Una columna, elementos apilados

---

## 🚀 Próximas Mejoras Sugeridas

1. **Integración con Backend**
   - Crear servicio `GestionVentasService`
   - Consumir API de productos reales
   - Persistir ventas en base de datos

2. **Funcionalidades Avanzadas**
   - Descuentos por cliente
   - Cupones de promoción
   - Historial de ventas
   - Reportes y estadísticas
   - Impresión de comprobantes

3. **Validaciones Mejoradas**
   - Verificar stock disponible
   - Validar horarios de venta
   - Auditoría de cambios

4. **UX/UI**
   - Dark mode
   - Búsqueda rápida de productos
   - Autocompletado de clientes
   - Carrito persistente

---

## 📚 Referencias

- [Angular Standalone Components](https://angular.io/guide/standalone-components)
- [Angular Forms](https://angular.io/guide/reactive-forms)
- [Bootstrap 5](https://getbootstrap.com/)
- [Font Awesome](https://fontawesome.com/)

---

## ✅ Lista de Requisitos Completados

✔️ **1. Crear el nuevo componente Angular**
- Componente standalone creado en `gestion-ventas/`

✔️ **2. Elegir un proceso del backend**
- Proceso seleccionado: **Gestión de Ventas**
- Incluye: captura de cliente, selección de productos, cálculo de totales

✔️ **3. Maquetación mínima obligatoria**
- ✓ Campos de entrada: Cliente, Producto, Cantidad, Notas
- ✓ Agrupación visual: Cards Bootstrap y tabla de resumen
- ✓ Botones principales: Agregar Producto, Registrar Venta, Limpiar

✔️ **4. Registrar la ruta**
- Ruta registrada en `entity.routes.ts` como `/gestion-ventas`

✔️ **5. Enlace visible en el menú**
- Item agregado al navbar dinámicamente
- Visible en dropdown "Entities"

---

**Componente desarrollado**: Diciembre 2025  
**Versión**: 1.0.0  
**Estado**: ✅ Completado y Funcional
