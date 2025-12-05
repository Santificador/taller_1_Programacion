# 🚀 Sistema de Venta de Café - JHipster Edition

## 📋 Descripción

Aplicación completa de gestión de ventas de café generada con **JHipster** usando modelado **JDL** (JHipster Domain Language).

Esta versión incluye:
- ✅ Backend Spring Boot con Java 17
- ✅ Frontend React moderno
- ✅ Base de datos PostgreSQL
- ✅ Autenticación JWT
- ✅ Auditoría automática
- ✅ Gestión de clientes y productos
- ✅ Sistema de ventas con detalles
- ✅ Cupones y descuentos
- ✅ Reportes y análisis

## 🎯 Características Principales

### 1. **Gestión de Productos (Cafés)**
- Catálogo de cafés con descripción, precio y origen
- Tipos de tostado (claro, medio, oscuro)
- Control de inventario (stock)
- Disponibilidad de productos

### 2. **Gestión de Clientes**
- Registro completo de clientes
- Información de contacto y dirección
- Historial de compras
- Datos de facturación

### 3. **Sistema de Ventas**
- Registro de ventas con múltiples ítems
- Cálculo automático de totales
- Estados de venta (pendiente, confirmada, entregada, cancelada)
- Aplicación de cupones y descuentos
- Detalles itemizado de cada venta

### 4. **Auditoría Automática**
- Trazabilidad de todos los cambios
- Registro de usuario y fecha/hora
- Valores anteriores y nuevos
- Dirección IP y observaciones

### 5. **Reportes y Análisis**
- Reportes de ventas por período
- Análisis de productos más vendidos
- Estadísticas por cliente
- Tendencias de ventas

### 6. **Seguridad**
- Autenticación JWT
- Autorización por roles
- Control de acceso a funcionalidades
- Gestión de usuarios

## 🛠️ Requisitos Previos

### Software Necesario

```
- Node.js v18 o superior
- npm v9 o superior
- JDK 17 o superior
- Maven 3.8 o superior
- PostgreSQL 12 o superior
- JHipster 8.1.0 o superior
- Git
```

### Instalación de JHipster

```bash
# Instalar JHipster globalmente
npm install -g generator-jhipster@latest

# Verificar instalación
jhipster --version
```

## 📦 Generación del Proyecto

### Paso 1: Generar la Aplicación desde JDL

```bash
# Navegar al directorio
cd venta-cafe-jhipster

# Generar el proyecto usando el archivo JDL
jhipster import-jdl venta-cafe.jdl --force --with-admin-ui

# O si ya tienes la estructura base
jhipster import-jdl venta-cafe.jdl
```

### Paso 2: Instalar Dependencias Frontend

```bash
# Las dependencias de npm se instalan automáticamente durante la generación
# Si no lo hace, ejecuta:
npm install
```

### Paso 3: Compilar el Proyecto

```bash
# Build completo (frontend + backend)
mvn clean package

# O para desarrollo
mvn clean compile
```

### Paso 4: Ejecutar la Aplicación

#### En Desarrollo

```bash
# Terminal 1 - Backend (Puerto 8080)
mvn spring-boot:run

# Terminal 2 - Frontend (Puerto 3000)
npm start
```

#### Con Docker

```bash
# Compilar para producción
mvn package -Pprod

# Construir y ejecutar contenedores
docker-compose up --build

# O sin rebuild
docker-compose up
```

## 🌐 Acceso a la Aplicación

Una vez que el proyecto esté en ejecución:

- **Frontend React**: http://localhost:3000
- **Backend API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **Base de Datos (Adminer)**: http://localhost:8081

## 📁 Estructura del Proyecto

```
venta-cafe-jhipster/
├── venta-cafe.jdl                # Modelado JDL
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ventacafe/
│   │   │       ├── config/           # Configuración
│   │   │       ├── domain/           # Entidades
│   │   │       ├── repository/       # Repositorios Spring Data
│   │   │       ├── service/          # Servicios
│   │   │       ├── service/dto/      # DTOs
│   │   │       ├── web/rest/         # Controladores REST
│   │   │       └── security/         # Seguridad JWT
│   │   ├── resources/
│   │   │   ├── application.yml       # Configuración
│   │   │   ├── i18n/                 # Internacionalización
│   │   │   └── db/changelog/         # Scripts Liquibase
│   │   └── webapp/
│   │       ├── app/
│   │       │   ├── components/       # Componentes React
│   │       │   ├── pages/            # Páginas
│   │       │   ├── services/         # Servicios API
│   │       │   └── reducers/         # Redux
│   │       ├── content/
│   │       └── styles/
│   └── test/
│       ├── java/                     # Tests unitarios
│       └── javascript/               # Tests e2e
├── node_modules/                     # Dependencias npm
├── target/                           # Build output
├── pom.xml                           # Maven
├── package.json                      # npm
├── package-lock.json                 # npm lock
├── docker-compose.yml                # Docker
├── Dockerfile                        # Docker image
└── README.md
```

## 🔐 Autenticación

### Login por Defecto (En Desarrollo)

```
Usuario: admin
Contraseña: admin
```

### Crear Nuevo Usuario

1. Ir a: Administración → Gestión de Usuarios
2. Hacer clic en "Crear nuevo Usuario"
3. Completar formulario
4. Asignar roles
5. Guardar

## 📊 Entidades del Sistema

### 1. **Cafe**
- ID, Nombre, Descripción
- Precio, Origen, Tipo de Tostado
- Stock, Disponible
- Fechas de creación/modificación

### 2. **Cliente**
- ID, Nombre, Apellido
- Email, Teléfono
- Dirección, Ciudad, Código Postal, País
- Activo, Fecha de Registro

### 3. **Venta**
- ID, Cantidad, Precio Unitario
- Monto Total, Fecha
- Estado (Pendiente, Confirmada, Entregada, Cancelada)
- Creado por, Fecha de Creación
- Modificado por, Fecha de Modificación

### 4. **DetalleVenta**
- ID, Cantidad, Precio Unitario
- Subtotal, Descuento %
- Descuento Monto, Total Línea

### 5. **Usuario**
- ID, Nombre, Correo
- Teléfono, Rol
- Activo, Fecha de Registro

### 6. **Auditoria**
- ID, Nombre Entidad, Acción
- Usuario, Fecha/Hora
- ID Registro, Valores Anteriores
- Valores Nuevos, Detalles, IP

### 7. **Cupon**
- ID, Código (único)
- Descripción, Descuento %
- Descuento Fijo, Fechas Vigencia
- Cantidad de Usos, Límite Máximo
- Activo

### 8. **Reporte**
- ID, Título, Descripción
- Tipo, Fecha de Generación
- Período Inicio/Fin, Datos

## 🔄 Flujo de Trabajo

### Registrar una Venta

1. **Seleccionar Cliente**
   - Buscar cliente existente o crear nuevo
   
2. **Agregar Productos**
   - Seleccionar café del catálogo
   - Especificar cantidad
   - El sistema calcula automáticamente el subtotal
   
3. **Aplicar Descuentos (Opcional)**
   - Ingresar código de cupón
   - Sistema valida y aplica descuento
   
4. **Confirmar Venta**
   - Revisar detalles
   - Confirmar
   - Sistema registra automáticamente en auditoría

5. **Actualizar Estado**
   - Pendiente → Confirmada → Entregada
   - Cada cambio se registra en auditoría

### Generar Reportes

1. Ir a "Reportes" en el menú
2. Seleccionar tipo de reporte
3. Elegir período
4. Hacer clic en "Generar"
5. Descargar en PDF o Excel

## 🧪 Testing

### Tests Unitarios (Backend)

```bash
# Ejecutar todos los tests
mvn test

# Tests de una clase específica
mvn test -Dtest=ClienteServiceTest

# Con cobertura
mvn test jacoco:report
```

### Tests de Integración (Frontend)

```bash
# Tests React
npm test

# Tests e2e con Protractor
npm run e2e
```

## 🚀 Deploy en Producción

### Build para Producción

```bash
# Compilar con perfil prod
mvn clean package -Pprod

# Resultado: target/ventacafe-[version].war
```

### Con Docker

```bash
# Construir imagen
docker build -f src/main/docker/Dockerfile.jvm -t ventacafe:1.0 .

# Ejecutar contenedor
docker run -p 8080:8080 ventacafe:1.0
```

### Variables de Entorno Producción

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://prod-db:5432/ventacafe
export SPRING_DATASOURCE_USERNAME=admin
export SPRING_DATASOURCE_PASSWORD=secure_password
export SPRING_JPA_HIBERNATE_DDL_AUTO=validate
export SERVER_PORT=8080
```

## 📈 Monitoreo y Logs

### Logs en Desarrollo

```bash
# Todos los logs
tail -f target/classes/application.log

# Logs de errores
tail -f target/classes/error.log

# Con filtro
tail -f target/classes/application.log | grep ERROR
```

### Métricas con Spring Boot Actuator

```
http://localhost:8080/management/actuator
http://localhost:8080/management/metrics
http://localhost:8080/management/health
```

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork del proyecto
2. Crear rama para la característica (`git checkout -b feature/amazing-feature`)
3. Commit cambios (`git commit -m 'Add some amazing feature'`)
4. Push a la rama (`git push origin feature/amazing-feature`)
5. Abrir Pull Request

## 📝 Changelog

### Versión 1.0.0 (Inicial)
- ✅ Proyecto base generado con JHipster 8.1.0
- ✅ Entidades principales: Cafe, Cliente, Venta, Usuario
- ✅ Sistema de auditoría automática
- ✅ Frontend React con Redux
- ✅ Backend Spring Boot con JWT
- ✅ Base de datos PostgreSQL
- ✅ Docker Compose configuration

## 📞 Soporte

Para problemas o preguntas:

1. Revisar la [documentación oficial de JHipster](https://www.jhipster.tech/)
2. Consultar [Spring Boot documentation](https://spring.io/projects/spring-boot)
3. Revisar [React documentation](https://reactjs.org/)
4. Abrir un issue en el repositorio

## 📄 Licencia

Este proyecto está bajo licencia Apache 2.0

---

**Generado con JHipster 8.1.0 ❤️**

Para regenerar o actualizar el proyecto:
```bash
jhipster import-jdl venta-cafe.jdl --force
```
