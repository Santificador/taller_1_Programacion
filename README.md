# ☕ Proyecto Java EDD - Gestión de Clientes y Ventas para Cafetería

Este proyecto ha sido desarrollado como parte de la **Evaluación Continua de los módulos de Programación y Entornos de Desarrollo** del ciclo de Desarrollo de Aplicaciones Multiplataforma (DAM).

El objetivo principal es crear una aplicación en Java, con interfaz de consola, que permita llevar la gestión básica de un pequeño negocio (en este caso, una tienda de café), incorporando prácticas profesionales como:

- Control de versiones con Git y GitHub
- Estructura modular y clara del código
- Documentación técnica con Javadoc
- Pruebas unitarias con JUnit
- Gestión de dependencias con Maven

La aplicación está diseñada para gestionar:
- Clientes
- Productos (cafés)
- Ventas asociadas

---

## 📋 Funcionalidades implementadas

A continuación se detallan las funcionalidades obligatorias desarrolladas en este proyecto:

### 👤 Gestión de clientes
- Alta de nuevos clientes
- Baja de clientes existentes
- Modificación de datos de un cliente
- Búsqueda de clientes por DNI
- Listado completo de clientes registrados

### ☕ Gestión de productos (cafés)
- Alta de productos disponibles en la tienda
- Listado general del catálogo de cafés
- Búsqueda de productos por especie

### 💰 Gestión de ventas
- Creación de nuevas ventas:
  - Selección de cliente existente
  - Añadir uno o más cafés del catálogo
- Visualización de todas las ventas realizadas
- Visualización de ventas por cliente
- Cálculo y muestra del importe total de cada venta

---

## 🛠 Requisitos técnicos

- Java JDK 17 o superior
- IntelliJ IDEA (recomendado)
- Git instalado y configurado
- Maven (para gestión de dependencias y testing)

---

## 🗂 Estructura del proyecto

La estructura del repositorio sigue el estándar Maven y mantiene una separación clara entre código, pruebas y configuración:

```text
Proyecto-Java-EDD/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/tiendacafe/... ← Código fuente principal
│   └── test/
│       └── java/
│           └── com/tiendacafe/... ← Pruebas unitarias (JUnit)
├── pom.xml         ← Configuración de Maven
├── README.md       ← Este archivo

```

## 🌿 Estrategia de ramas Git

Este proyecto utiliza una estrategia de ramas profesional para facilitar el trabajo modular y evitar conflictos.

### 🔀 Ramas principales

- **`main`**  
  Rama estable. Contiene versiones funcionales listas para entrega.

- **`dev`**  
  Rama de desarrollo general. Aquí se integran las funcionalidades probadas de las ramas `feature/*`.

### 🌱 Ramas por funcionalidad (`features-*`)

Cada funcionalidad principal del proyecto se desarrolla en su propia rama:

- **`features-cliente`**
  - Clase `Cliente.java`
  - Pruebas unitarias en `ClienteTest.java`
  - Javadoc generado para Cliente

- **`features-cafe`**
  - Clase `Cafe.java`
  - Pruebas unitarias en `CafeTest.java`
  - Javadoc generado para Cafe

- **`features-venta`**
  - Clase `Venta.java`
  - Pruebas unitarias en `VentaTest.java`
  - Javadoc generado para Venta

- **`features-menu`**
  - Clase principal que contiene el menú interactivo
  - Coordinación de navegación entre funcionalidades

### 🔁 Flujo de trabajo

1. Se desarrolla una funcionalidad en su rama `features-*`.
2. Una vez completada y probada, se fusiona con `dev`.
3. Cuando `dev` está estable, se fusiona con `main`.

---

## 📚 Documentación técnica (Javadoc)

Este proyecto incluye documentación técnica generada con Javadoc.

### 📄 Cómo acceder

- Abre el archivo:  
  `docs/index.html`

- O accede directamente a la clase `Cliente`:  
  `docs/com/tiendacafe/Cliente.html`

> Si abres `index.html`, podrás navegar por toda la documentación del proyecto.

### 🛠 Cómo regenerar la documentación (opcional)

Desde IntelliJ IDEA:

1. Ve al menú `Tools` → `Generate JavaDoc...`
2. Selecciona `Whole project`
3. Define el directorio de salida como `./docs`
4. Pulsa `Generate`





