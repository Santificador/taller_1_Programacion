# 🚀 Proyecto JHipster - Sistema de Venta de Café

## 📋 Requisitos Previos

Antes de generar el proyecto, asegúrate de tener instalado:

### 1. Node.js y npm
- **Descargar**: https://nodejs.org/
- **Recomendado**: LTS (Long Term Support)
- **Verificar instalación**:
  ```powershell
  node --version
  npm --version
  ```

### 2. Java Development Kit (JDK)
- **Mínimo**: JDK 11 (Recomendado JDK 17)
- **Verificar**: `java -version`

### 3. Maven
- **Verificar**: `mvn --version`

### 4. Git
- **Descargar**: https://git-scm.com/
- **Verificar**: `git --version`

## 📦 Instalación de JHipster CLI

Una vez que tengas Node.js instalado, ejecuta:

```powershell
npm install -g generator-jhipster
```

**Verificar instalación**:
```powershell
jhipster --version
```

## 🎯 Generar el Proyecto

### Opción 1: Desde el archivo JDL (Recomendado)

```powershell
cd c:\Users\Pau\OneDrive\Escritorio\Presi\Examen_final_taller_4\jhipster-venta-cafe

# Generar desde JDL
jhipster import-jdl venta-cafe.jdl --force --no-skip-server --no-skip-client
```

### Opción 2: Generación Interactiva

```powershell
jhipster
```

Seleccionar opciones:
- Application type: **Monolithic application**
- Base name: **ventaCafeApp**
- Default package name: **com.ventacafe**
- Server port: **8080**
- Database: **PostgreSQL**
- Cache: **Ehcache**
- Build tool: **Maven**
- Client framework: **Angular**
- Testing: **Cypress**

## 🏗️ Estructura del Proyecto Generado

```
ventaCafeApp/
├── src/
│   ├── main/
│   │   ├── java/          # Backend Spring Boot
│   │   ├── resources/     # Configuración y datos iniciales
│   │   └── webapp/        # Frontend Angular
│   ├── test/              # Tests
├── pom.xml                # Dependencias Maven
├── package.json           # Dependencias Node.js
├── angular.json           # Configuración Angular
└── ...
```

## 🚀 Ejecutar la Aplicación

### 1. Instalar dependencias Node.js (primera vez)

```powershell
npm install
```

### 2. Iniciar la base de datos PostgreSQL

```powershell
# Si tienes Docker
docker-compose up -d

# O usa PostgreSQL instalado localmente
```

### 3. Ejecutar la aplicación

**Opción A: Desarrollo (Frontend + Backend simultáneamente)**

```powershell
npm start
```

**Opción B: Solo Backend**

```powershell
mvn
# O con Maven wrapper
./mvnw
# O en Windows
mvnw.cmd
```

**Opción C: Solo Frontend**

```powershell
npm run client:dev
```

### 4. Acceder a la Aplicación

- **Aplicación**: http://localhost:4200
- **API REST**: http://localhost:8080/api/
- **Swagger UI**: http://localhost:8080/swagger-ui/
- **Administrador H2**: http://localhost:8080/h2-console (si usas H2)

## 👤 Credenciales por Defecto

- **Usuario**: admin
- **Contraseña**: admin

## 📁 Estructura del Modelado JDL

El archivo `venta-cafe.jdl` define:

### Entidades
- **Usuario**: Usuarios del sistema con roles
- **Cliente**: Clientes de la tienda
- **Cafe**: Productos de café
- **Venta**: Registros de ventas
- **Auditoria**: Trazabilidad de cambios

### Características
- Autenticación JWT
- Base de datos PostgreSQL
- Frontend Angular
- API REST automática
- DTOs con MapStruct
- Servicios de negocio
- Búsqueda con Elasticsearch
- Paginación e infinite-scroll
- Tests con Cypress

## 🔧 Configuración Adicional

### Conectar a PostgreSQL

**Archivo**: `src/main/resources/application.yml`

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ventacafe
    username: admin
    password: admin
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQL10Dialect
```

### Configurar correo

Editar `application.yml`:

```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: tu-email@gmail.com
    password: tu-contraseña
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
```

## 📚 Comandos Útiles

```powershell
# Compilar
mvn clean package

# Ejecutar tests
mvn test

# Frontend
npm run build

# Generar código desde JDL nuevamente
jhipster import-jdl venta-cafe.jdl --force

# Limpiar caché
npm run clean

# Ver versiones de dependencias
npm outdated
```

## 🐳 Docker Compose

Para ejecutar con Docker:

```powershell
# Genera el Dockerfile (JHipster lo crea automáticamente)
mvn -Pprod -DskipTests clean verify

# Ejecutar con docker-compose
docker-compose -f docker-compose.yml up -d
```

## 📖 Documentación

- **JHipster Official**: https://www.jhipster.tech/
- **Angular**: https://angular.io/
- **Spring Boot**: https://spring.io/projects/spring-boot
- **PostgreSQL**: https://www.postgresql.org/

## ❓ Solución de Problemas

### Error: npm no reconocido
- Instalar Node.js desde https://nodejs.org/
- Reiniciar la terminal/PowerShell

### Error: jhipster no reconocido
- Ejecutar: `npm install -g generator-jhipster`
- Reiniciar PowerShell

### Puerto 8080 en uso
- Cambiar el puerto en `application.yml`
- O liberar el puerto: `netstat -ano | findstr :8080`

### Problema de conexión a BD
- Verificar que PostgreSQL esté ejecutándose
- Verificar credenciales en `application.yml`

## 🎓 Próximos Pasos

1. ✅ Instalar Node.js y JHipster
2. ✅ Ejecutar: `jhipster import-jdl venta-cafe.jdl`
3. ✅ Configurar PostgreSQL
4. ✅ Ejecutar: `npm start`
5. ✅ Acceder a http://localhost:4200
6. ✅ Explorar la aplicación generada

---

**¡JHipster generará toda la estructura, APIs, interfaz y seguridad automáticamente!** 🎉
