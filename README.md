<<<<<<< HEAD
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



=======
# taller_1_Programacion

Preparación del Entorno de Desarrollo Java + Angular
1. Creación del Repositorio
1.1. Creación de cuenta en GitHub (si no la tiene).
1.2. Creación del repositorio público.
1.3. Configuración inicial del repositorio:

Archivo README.md

Descripción del proyecto

Primer commit y push

2. Instalación del Entorno Backend (Java)
2.1. Instalación de SDKMAN!
2.2. Instalación de Java 17 o 21 mediante SDKMAN
2.3. Instalación de Maven (o Gradle opcional)
2.4. Verificación de versiones instaladas
2.5. Creación de la estructura inicial del proyecto Java
2.6. Subida del proyecto Java al repositorio

3. Instalación del Entorno Frontend (Angular)
3.1. Instalación de NVM (Node Version Manager)
3.2. Instalación de Node.js LTS con NVM
3.3. Instalación de Angular CLI
3.4. Verificación del entorno (node, npm, ng version)
3.5. Creación del proyecto Angular
3.6. Subida del proyecto Angular al repositorio

4. Instalación y Prueba de Docker
4.1. Instalación de Docker Desktop según SO
4.2. Validación de instalación con docker version
4.3. Ejecución del contenedor de prueba hello-world
4.4. Captura de pantalla como evidencia
4.5. Documentación en el README

5. Documentación y Evidencias
5.1. Incluir capturas de las instalaciones (Java, Maven, Node, Angular, Docker)
5.2. Registrar comandos utilizados
5.3. Descripción de ventajas de usar SDKMAN, NVM y Docker
5.4. Pasos para reproducir el entorno en otra máquina

6. Entrega Final
6.1. URL del repositorio GitHub
6.2. Verificación de commits organizados
6.3. README completo y actualizado
>>>>>>> cd321b82e0cfb87bba3690d1092f84657513828c



Crear un Nuevo Componente Angular con un Botón “Volver al Inicio”- ASISTENCIA CLASE 01-12
Fecha de entrega Viernes a las 23:59 Puntos 100 Entregando un cuadro de entrada de texto Disponible 1 de dic en 0:00 - 5 de dic en 23:59
✔️ 1. Crear un nuevo componente Angular
Dentro del proyecto JHipster (carpeta src/main/webapp/app), el alumno debe crear un componente llamado: volver-inicio

El comando CLI correspondiente: ng generate component volver-inicio o ng g c volver-inicio

Esto debe crear automáticamente:

volver-inicio.component.ts

volver-inicio.component.html

volver-inicio.component.scss

volver-inicio.component.spec.ts

✔️ 2. Agregar el HTML del botón
En el archivo generado src/main/webapp/app/volver-inicio/volver-inicio.component.html

<button class="btn btn-primary" (click)="volverAlInicio()">
  Volver al inicio
</button>

 

✔️ 3. Implementar la navegación en el componente
En el archivo:  volver-inicio.component.ts

import { Component } from '@angular/core';

import { Router } from '@angular/router';

 

@Component({

  selector: 'jhi-volver-inicio',

  templateUrl: './volver-inicio.component.html',

  styleUrls: ['./volver-inicio.component.scss']

})

export class VolverInicioComponent {

  constructor(private router: Router) {}

 

  volverAlInicio(): void {

    this.router.navigate(['/']);

  }

}

✔️ 4. Registrar la ruta en Angular
En el archivo: src/main/webapp/app/app-routing.module.ts o el correspondiente al generado en sus proyectos.

{

  path: 'volver',

  component: VolverInicioComponent,

}

 

Esto permitirá navegar al componente mediante: 

http://localhost:8080/#/volver

 

✔️ 5. Agregar un enlace visible en la app
En src/main/webapp/app/layouts/navbar/navbar.component.html o  el correspondiente generado en sus proyectos agregar :


<li class="nav-item">

  <a class="nav-link" routerLink="/volver">Volver</a>

</li>

✔️ 6. Validar funcionamiento
Puede entrar a /volver

El botón aparece y funciona

Lo lleva al inicio correctamente
