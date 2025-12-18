# 🏗️ Resumen Técnico del Proyecto JHipster

## Información General

**Nombre del Proyecto:** Venta Café  
**Tecnología:** JHipster 8.x  
**Fecha de Creación:** Diciembre 2025  
**Estado:** ✅ Completado y en ejecución  

---

## Stack Tecnológico

### Backend
- **Framework:** Spring Boot 3.4.5
- **Lenguaje:** Java 17 LTS (Actualizado a 21 LTS)
- **ORM:** Hibernate 6.x
- **Data Access:** Spring Data JPA
- **Build:** Maven 3.9.x
- **Base de Datos:** PostgreSQL 15
- **Migraciones:** Liquibase

### Frontend
- **Framework:** Angular 18
- **Lenguaje:** TypeScript 5.x
- **Estilos:** SCSS / Bootstrap 5.1
- **Build:** npm / Angular CLI
- **Bundler:** Webpack

### DevOps
- **Containerización:** Docker & Docker Compose
- **Control de Versiones:** Git
- **CI/CD:** No configurado (puede agregarse)

---

## Estructura del Proyecto

```
venta-cafe-jhipster/
│
├── src/
│   ├── main/
│   │   ├── java/com/ventacafe/
│   │   │   ├── VentacafeappApplication.java      # Main class
│   │   │   ├── config/                           # Spring Configuration
│   │   │   │   ├── WebConfigurer.java
│   │   │   │   ├── SecurityConfiguration.java
│   │   │   │   └── JHipsterProperties.java
│   │   │   ├── domain/                           # JPA Entities
│   │   │   │   ├── Producto.java
│   │   │   │   ├── Categoria.java
│   │   │   │   ├── Venta.java
│   │   │   │   ├── DetalleVenta.java
│   │   │   │   ├── Cliente.java
│   │   │   │   ├── Usuario.java
│   │   │   │   └── Authority.java
│   │   │   ├── repository/                       # Spring Data Repositories
│   │   │   │   ├── ProductoRepository.java
│   │   │   │   ├── CategoriaRepository.java
│   │   │   │   ├── VentaRepository.java
│   │   │   │   ├── DetalleVentaRepository.java
│   │   │   │   ├── ClienteRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   ├── service/                          # Business Logic
│   │   │   │   ├── ProductoService.java
│   │   │   │   ├── CategoriaService.java
│   │   │   │   ├── VentaService.java
│   │   │   │   ├── ClienteService.java
│   │   │   │   └── UserService.java
│   │   │   ├── web/rest/                         # REST Controllers
│   │   │   │   ├── ProductoResource.java
│   │   │   │   ├── CategoriaResource.java
│   │   │   │   ├── VentaResource.java
│   │   │   │   ├── ClienteResource.java
│   │   │   │   ├── AccountResource.java
│   │   │   │   └── UserResource.java
│   │   │   ├── web/rest/vm/                      # View Models
│   │   │   │   └── LoginVM.java
│   │   │   ├── service/dto/                      # Data Transfer Objects
│   │   │   │   ├── ProductoDTO.java
│   │   │   │   ├── CategoriaDTO.java
│   │   │   │   └── ...
│   │   │   ├── service/mapper/                   # DTO Mappers
│   │   │   │   ├── ProductoMapper.java
│   │   │   │   ├── CategoriaMapper.java
│   │   │   │   └── ...
│   │   │   └── security/
│   │   │       ├── AuthoritiesConstants.java
│   │   │       ├── SecurityUtils.java
│   │   │       └── jwt/
│   │   │           └── TokenProvider.java
│   │   ├── webapp/
│   │   │   ├── app/
│   │   │   │   ├── app.config.ts
│   │   │   │   ├── app.routes.ts                 # Routing
│   │   │   │   ├── layouts/                      # Layout components
│   │   │   │   │   ├── main/
│   │   │   │   │   ├── navbar/
│   │   │   │   │   └── footer/
│   │   │   │   ├── entities/                     # Entity modules
│   │   │   │   │   ├── producto/
│   │   │   │   │   ├── categoria/
│   │   │   │   │   ├── venta/
│   │   │   │   │   ├── detalle-venta/
│   │   │   │   │   └── cliente/
│   │   │   │   ├── volver-inicio/               # Custom component
│   │   │   │   ├── admin/
│   │   │   │   └── home/
│   │   │   ├── content/
│   │   │   │   ├── scss/
│   │   │   │   │   ├── _bootstrap-variables.scss
│   │   │   │   │   ├── global.scss
│   │   │   │   │   └── ...
│   │   │   │   └── images/
│   │   │   └── index.html
│   │   ├── resources/
│   │   │   ├── config/
│   │   │   │   ├── application.yml
│   │   │   │   ├── application-dev.yml
│   │   │   │   ├── application-prod.yml
│   │   │   │   └── liquibase/
│   │   │   │       └── master.xml
│   │   │   └── db/
│   │   │       └── migration/
│   │   │           └── (Liquibase XML files)
│   ├── test/
│   │   ├── java/com/ventacafe/
│   │   │   └── (Test classes)
│   │   └── resources/
│   │       └── (Test configuration)
│
├── target/
│   ├── classes/                  # Compiled classes
│   ├── test-classes/             # Test classes
│   └── ventacafeapp-0.0.1-SNAPSHOT.jar  # Executable JAR (78.23 MB)
│
├── pom.xml                       # Maven configuration
├── package.json                  # npm configuration
├── angular.json                  # Angular configuration
├── tsconfig.json                 # TypeScript configuration
├── jest.conf.js                  # Jest test configuration
├── eslint.config.mjs             # ESLint configuration
├── venta-cafe.jdl                # JHipster JDL model
├── checkstyle.xml                # Code style rules
├── sonar-project.properties      # SonarQube configuration
└── README.md                      # Project documentation
```

---

## Dependencias Principales

### Backend (pom.xml)

```xml
<!-- Spring Boot -->
<spring-boot.version>3.4.5</spring-boot.version>

<!-- Spring Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- PostgreSQL Driver -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>

<!-- Liquibase -->
<dependency>
    <groupId>org.liquibase</groupId>
    <artifactId>liquibase-core</artifactId>
</dependency>

<!-- Spring Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<!-- JWT -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
</dependency>

<!-- Springdoc OpenAPI (Swagger) -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
</dependency>
```

### Frontend (package.json)

```json
{
  "dependencies": {
    "@angular/common": "^18.0.0",
    "@angular/compiler": "^18.0.0",
    "@angular/core": "^18.0.0",
    "@angular/forms": "^18.0.0",
    "@angular/platform-browser": "^18.0.0",
    "@angular/platform-browser-dynamic": "^18.0.0",
    "@angular/router": "^18.0.0",
    "bootstrap": "^5.1.3",
    "ngx-bootstrap": "^13.0.0",
    "ngx-infinite-scroll": "^13.0.0",
    "ngx-toast-notifications": "^1.4.0"
  },
  "devDependencies": {
    "@angular-devkit/build-angular": "^18.0.0",
    "@angular/cli": "^18.0.0",
    "@angular/compiler-cli": "^18.0.0",
    "typescript": "^5.0.0"
  }
}
```

---

## Configuración de Base de Datos

### Entidades JPA Generadas

#### 1. Producto
```java
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Size(max = 1000)
    @Column(name = "descripcion")
    private String descripcion;
    
    @NotNull
    @DecimalMin("0")
    @Column(name = "precio", nullable = false)
    private BigDecimal precio;
    
    @NotNull
    @Min(0)
    @Column(name = "stock", nullable = false)
    private Integer stock;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
```

#### 2. Categoria
```java
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    
    @Size(max = 1000)
    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToMany(mappedBy = "categoria")
    private Set<Producto> productos;
}
```

#### 3. Venta
```java
@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "fecha", nullable = false)
    private Instant fecha;
    
    @NotNull
    @DecimalMin("0")
    @Column(name = "total", nullable = false)
    private BigDecimal total;
    
    @Column(name = "estado")
    private String estado;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "venta")
    private Set<DetalleVenta> detalles;
}
```

#### 4. DetalleVenta
```java
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Min(1)
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
    
    @NotNull
    @DecimalMin("0")
    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;
    
    @NotNull
    @DecimalMin("0")
    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;
    
    @ManyToOne(required = true)
    @JoinColumn(name = "venta_id")
    private Venta venta;
    
    @ManyToOne(required = true)
    @JoinColumn(name = "producto_id")
    private Producto producto;
}
```

---

## Endpoints REST Disponibles

### Productos
```
GET    /api/productos          - Obtener todos (con paginación)
GET    /api/productos/{id}     - Obtener por ID
POST   /api/productos          - Crear nuevo
PUT    /api/productos/{id}     - Actualizar
DELETE /api/productos/{id}     - Eliminar
```

### Categorías
```
GET    /api/categorias         - Obtener todas
GET    /api/categorias/{id}    - Obtener por ID
POST   /api/categorias         - Crear nueva
PUT    /api/categorias/{id}    - Actualizar
DELETE /api/categorias/{id}    - Eliminar
```

### Ventas
```
GET    /api/ventas             - Obtener todas
GET    /api/ventas/{id}        - Obtener por ID
POST   /api/ventas             - Crear nueva
PUT    /api/ventas/{id}        - Actualizar
DELETE /api/ventas/{id}        - Eliminar
```

### Clientes
```
GET    /api/clientes           - Obtener todos
GET    /api/clientes/{id}      - Obtener por ID
POST   /api/clientes           - Crear nuevo
PUT    /api/clientes/{id}      - Actualizar
DELETE /api/clientes/{id}      - Eliminar
```

---

## Flujo de Ejecución

### 1. Compilación
```bash
mvn clean package -DskipTests -P webapp
```
- ✅ Maven limpia artefactos previos
- ✅ npm install instala dependencias JavaScript
- ✅ npm run webapp:prod compila Angular
- ✅ Maven compila Java
- ✅ Se genera JAR de 78.23 MB

### 2. Inicio de Base de Datos
```bash
docker-compose up -d
```
- ✅ PostgreSQL 15 inicia en puerto 5432
- ✅ Adminer inicia en puerto 8081

### 3. Ejecución de la Aplicación
```bash
java -Xmx512m -jar target/ventacafeapp-0.0.1-SNAPSHOT.jar
```
- ✅ Spring Boot se inicializa
- ✅ Liquibase ejecuta migraciones
- ✅ Conexión a Base de Datos se establece
- ✅ Hibernate genera tablas
- ✅ Tomcat inicia en puerto 8080

### 4. Acceso
- Frontend: http://localhost:8080
- API REST: http://localhost:8080/api/**
- Base de Datos: http://localhost:8081

---

## Configuración de Perfiles (Profiles)

### Profile: dev
- **Activo por defecto**
- Base de datos en localhost
- Logs detallados
- Hot reload habilitado
- CORS permisivo

### Profile: prod
- Base de datos en servidor remoto
- Logs mínimos
- Compresión habilitada
- Caché agresivo

---

## Seguridad

### Autenticación
- JWT (JSON Web Tokens)
- Spring Security
- Contraseñas hasheadas (BCrypt)

### Autorización
- Role-based access control (RBAC)
- Roles: ADMIN, USER
- Validación en controladores y servicios

### CORS
```java
@Configuration
public class WebConfigurer {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                    .allowedOrigins("http://localhost:*")
                    .allowedMethods("*")
                    .allowedHeaders("*")
                    .allowCredentials(true);
            }
        };
    }
}
```

---

## Compilación Angular

Cuando usas la opción `-P webapp`:

1. **npm install** instala todas las dependencias
2. **ng build --prod** compila Angular para producción
3. **Los bundles se copian a classpath:/static/**
4. **El JAR incluye la interfaz compilada**

**Resultado:** JAR totalmente funcional con frontend incluido

---

## Información de Contacto / Soporte

Para más información sobre JHipster:
- https://www.jhipster.tech/
- https://start.jhipster.tech/
- Documentación: https://www.jhipster.tech/documentation-archive/v8.0.0/

---

**Documento generado:** 17/12/2025  
**Versión del Proyecto:** 0.0.1-SNAPSHOT  
**Estado:** ✅ Producción Lista
