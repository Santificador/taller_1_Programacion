# 🧪 Guía de Prueba - Componente Gestión de Ventas

## 🚀 Cómo Acceder al Componente

### Opción 1: Desde el Navbar
1. Inicia el servidor: `npm start`
2. Navega a `http://localhost:4200`
3. En el navbar superior, haz clic en el dropdown **"Entities"**
4. Selecciona **"Gestión de Ventas"**
5. ¡Listo! Estás en el componente

### Opción 2: URL Directa
```
http://localhost:4200/gestion-ventas
```

---

## ✅ Casos de Prueba

### Caso 1: Validación del Formulario
**Objetivo**: Verificar que los campos se validen correctamente

**Pasos**:
1. Abre el componente
2. Intenta hacer clic en "Agregar Producto" sin llenar nada
   - ❌ Esperado: Botón deshabilitado
3. Ingresa "ab" en cliente
   - ❌ Esperado: Mensaje de error (mínimo 3 caracteres)
4. Ingresa "Juan Pérez" (nombre válido)
   - ✅ Esperado: Se acepta
5. Intenta agregar producto sin seleccionar uno
   - ❌ Esperado: Botón deshabilitado

---

### Caso 2: Agregar Productos
**Objetivo**: Verificar la adición de productos a la venta

**Pasos**:
1. Completa el formulario:
   - Cliente: "Carlos López"
   - Producto: "Café Espresso"
   - Cantidad: "2"

2. Click en "Agregar Producto"
   - ✅ Esperado: 
     - El producto aparece en la tabla
     - Muestra: "Café Espresso | 2 | $25.00 | $50.00"
     - El total actualiza a $50.00

3. Agrega otro producto:
   - Cliente: "Carlos López" (ya está)
   - Producto: "Cappuccino"
   - Cantidad: "1"

4. Click en "Agregar Producto"
   - ✅ Esperado:
     - Aparece segunda fila en tabla
     - Total actualiza a $82.00 ($50 + $32)

---

### Caso 3: Productos Duplicados
**Objetivo**: Verificar que agregar el mismo producto aumenta la cantidad

**Pasos**:
1. Agrega "Café Latte" x 2
2. Observa: Total = $60.00
3. Agrega "Café Latte" x 1 (mismo producto)
4. ✅ Esperado:
   - En lugar de 2 filas, hay 1 sola
   - La cantidad cambió a 3
   - El total es ahora $90.00 ($30 x 3)

---

### Caso 4: Eliminar Producto
**Objetivo**: Verificar que la eliminación actualice el total

**Pasos**:
1. Agrega 2 productos diferentes
   - Café Americano x 3 = $60.00
   - Mocca x 1 = $35.00
   - Total: $95.00

2. Haz clic en el botón 🗑️ de Café Americano
3. ✅ Esperado:
   - Se elimina del resumen
   - Total actualiza a $35.00
   - Queda solo Mocca

4. Elimina Mocca también
5. ✅ Esperado:
   - Tabla muestra "No hay productos agregados aún"
   - Total: $0.00
   - Botón "Registrar Venta" está deshabilitado

---

### Caso 5: Registrar Venta
**Objetivo**: Verificar que se registre correctamente

**Pasos**:
1. Completa una venta:
   - Cliente: "María García"
   - Agregar: Espresso x 2 = $50.00
   - Agregar: Latte x 1 = $30.00
   - Total: $80.00

2. Haz clic en "Registrar Venta"

3. ✅ Esperado:
   - Aparece alert: "Venta registrada exitosamente para María García. Total: $80.00"
   - En consola (F12 → Console) aparece:
     ```javascript
     {
       cliente: "María García",
       productos: [
         {id: 1, nombre: "Café Espresso", cantidad: 2, precioUnitario: 25},
         {id: 3, nombre: "Café Latte", cantidad: 1, precioUnitario: 30}
       ],
       total: 80,
       notas: "",
       fecha: "2025-12-08T14:30:00.000Z"
     }
     ```

4. El formulario se limpia automáticamente
5. Puedes iniciar nueva venta

---

### Caso 6: Limpiar Formulario
**Objetivo**: Verificar que se limpien todos los campos

**Pasos**:
1. Crea una venta con varios productos
2. Haz clic en "Limpiar Formulario"
3. ✅ Esperado:
   - Todos los campos se vacían
   - La tabla de productos desaparece
   - Total vuelve a $0.00
   - El componente está listo para nueva venta

---

### Caso 7: Notas Opcionales
**Objetivo**: Verificar que las notas se registren

**Pasos**:
1. Completa una venta
2. En el campo "Notas", ingresa: "Entregar mañana a las 10am"
3. Registra la venta
4. En consola, verifica que el objeto contenga:
   ```javascript
   notas: "Entregar mañana a las 10am"
   ```

---

### Caso 8: Responsividad
**Objetivo**: Verificar que el componente se adapte a diferentes pantallas

**Pasos (en DevTools)**:
1. Abre DevTools (F12)
2. Click en "Toggle device toolbar" (Ctrl+Shift+M)
3. Prueba diferentes resoluciones:

**Mobile (360px)**:
- ✅ Una columna
- ✅ Cards apiladas
- ✅ Tabla scrollable horizontal
- ✅ Botones adaptados

**Tablet (768px)**:
- ✅ Dos columnas pero ajustadas
- ✅ Todo legible

**Desktop (1920px)**:
- ✅ Dos columnas lado a lado
- ✅ Distribución óptima

---

### Caso 9: Cantidades Extremas
**Objetivo**: Verificar validaciones de cantidad

**Pasos**:
1. Intenta ingresar cantidad = 0
   - ❌ Esperado: Botón deshabilitado

2. Intenta ingresar cantidad = 101
   - ❌ Esperado: Campo marcado como inválido

3. Intenta cantidad = 50
   - ✅ Esperado: Se acepta correctamente

4. Intenta cantidad = -5
   - ❌ Esperado: Se rechaza o ajusta a 1

---

### Caso 10: Navegación
**Objetivo**: Verificar que la ruta funciona correctamente

**Pasos**:
1. Desde home, haz clic en "Gestión de Ventas"
2. ✅ Esperado: URL cambia a `/gestion-ventas`

3. Recarga la página (F5)
4. ✅ Esperado: El componente se mantiene visible

5. Usa botón atrás del navegador
6. ✅ Esperado: Vuelve a home

---

## 🎯 Pruebas de Integración Futura

Una vez integrado con el backend:

- [ ] Verificar que se consumen productos reales
- [ ] Verificar que se persisten en BD
- [ ] Verificar que se obtiene historial
- [ ] Verificar validaciones del servidor
- [ ] Verificar manejo de errores
- [ ] Verificar timeouts
- [ ] Verificar cache de productos

---

## 🐛 Posibles Problemas

| Problema | Solución |
|----------|----------|
| Botón "Registrar Venta" no se habilita | Verifica que hay al menos 1 producto y cliente válido |
| La tabla no muestra productos | Abre DevTools para ver errores en console |
| El menú no aparece en navbar | Reinicia el servidor con `npm start` |
| Estilos no se ven | Limpia el cache del navegador (Ctrl+Shift+Del) |
| Traducción no funciona | Verifica que el archivo i18n está en la carpeta correcta |

---

## 📊 Prueba de Rendimiento

**Cantidad máxima de productos simultáneos**: 20-30

**Pasos**:
1. Agrega 20 productos diferentes
2. Verifica que:
   - La tabla sigue siendo responsiva
   - Los cálculos son instantáneos
   - No hay lag en la interfaz

---

## 🎓 Resumen de Pruebas

```
✅ Validación de formulario
✅ Adición de productos
✅ Gestión de duplicados
✅ Eliminación de items
✅ Registro de venta
✅ Limpieza de formulario
✅ Notas adicionales
✅ Responsividad
✅ Validaciones de cantidad
✅ Navegación
✅ Rendimiento
```

---

## 📝 Reporte de Resultados

Después de ejecutar las pruebas, documenta:
- ✅ Pruebas pasadas: ___ de 10
- ❌ Pruebas fallidas: ___ de 10
- 🔄 Mejoras sugeridas:
  - _________________
  - _________________

---

**¡Listo para testing!** 🎉
