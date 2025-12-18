# 📸 Guía para Capturar Screenshots del Proyecto

## Instrucciones de Captura

Abre tu navegador en http://localhost:8080 y sigue estos pasos:

### Screenshot 1: Pantalla Principal
1. Ve a http://localhost:8080
2. Presiona `PrintScreen` o `Shift + F2` (captura de pantalla de Windows)
3. Guarda como `01_pantalla_principal.png`

### Screenshot 2: Navbar y Componente Volver
1. En la misma pantalla, busca el navbar superior
2. Localiza el link "Volver" (con flecha ←)
3. Captura el navbar completo

### Screenshot 3: Componente Volver al Inicio
1. Ve a http://localhost:8080/#/volver
2. Debería ver un botón "Volver al inicio" con estilo turquesa
3. Captura la pantalla

### Screenshot 4: Listado de Productos
1. Ve a http://localhost:8080/#/producto
2. Debería ver una tabla con productos
3. Captura la tabla completa

### Screenshot 5: Base de Datos (Adminer)
1. Ve a http://localhost:8081
2. Inicia sesión con usuario: `admin` y contraseña: `admin`
3. Selecciona la base de datos `ventacafe`
4. Navega a la tabla `producto`
5. Captura la vista de la tabla

### Screenshot 6: Terminal de Ejecución
1. En PowerShell, ve a la carpeta del proyecto
2. Ejecuta: `java -Xmx512m -jar venta-cafe-jhipster/target/ventacafeapp-0.0.1-SNAPSHOT.jar`
3. Captura los logs de inicio (especialmente "Web application fully configured")

### Screenshot 7: IDE - Estructura del Proyecto
1. Abre VS Code
2. Muestra la carpeta `venta-cafe-jhipster/src/main/java/com/ventacafe/`
3. Expande las carpetas: domain, repository, service, web
4. Captura la estructura del proyecto

### Screenshot 8: Archivo JDL
1. Abre `venta-cafe-jhipster/venta-cafe.jdl`
2. Muestra el contenido del archivo JDL
3. Captura el modelo de entidades

### Screenshot 9: pom.xml
1. Abre `venta-cafe-jhipster/pom.xml`
2. Muestra las dependencias principales (Spring Boot, JHipster, etc.)
3. Captura la sección de dependencias

### Screenshot 10: Componente Volver (Código)
1. Abre `venta-cafe-jhipster/src/main/webapp/app/volver-inicio/volver-inicio.component.ts`
2. Muestra el código del componente
3. Captura el componente TypeScript

---

## Guardar Screenshots

Después de capturar cada screenshot, guárdalo en:
```
evidencias_jhipster/screenshots/
```

Con nombres como:
- `01_pantalla_principal.png`
- `02_navbar.png`
- `03_componente_volver.png`
- `04_listado_productos.png`
- `05_base_datos.png`
- `06_terminal_logs.png`
- `07_estructura_proyecto.png`
- `08_archivo_jdl.png`
- `09_pom_xml.png`
- `10_componente_volver_codigo.png`

---

## URLs Importantes

| Descripción | URL |
|-------------|-----|
| Aplicación Principal | http://localhost:8080 |
| Componente Volver | http://localhost:8080/#/volver |
| Listado Productos | http://localhost:8080/#/producto |
| Listado Categorías | http://localhost:8080/#/categoria |
| Listado Ventas | http://localhost:8080/#/venta |
| Listado Clientes | http://localhost:8080/#/cliente |
| Base de Datos Adminer | http://localhost:8081 |

---

## Comando para Iniciar la App (si se cierra)

```bash
cd venta-cafe-jhipster
mvn clean package -DskipTests -P webapp
java -Xmx512m -jar target/ventacafeapp-0.0.1-SNAPSHOT.jar
```

---

## Notas Importantes

- ✅ La aplicación usa tema **turquesa moderno**
- ✅ Todos los botones tienen animaciones
- ✅ Las tablas tienen efectos hover
- ✅ El componente "Volver" aparece en el navbar
- ✅ La base de datos funciona correctamente
- ✅ Los endpoints REST están activos

---

¡Listo! Ya tienes todo preparado para capturar y documentar tu proyecto JHipster.
