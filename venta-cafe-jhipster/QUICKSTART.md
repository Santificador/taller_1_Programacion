# ⚡ Guía Rápida - JHipster + JDL

## 🎯 En 5 Minutos

### Opción 1: Script Automático (Recomendado)

#### En Windows:
```batch
generate.bat
```

#### En macOS/Linux:
```bash
chmod +x generate.sh
./generate.sh
```

El script se encargará de:
- ✅ Verificar JHipster, Node.js y Maven
- ✅ Crear la estructura del proyecto
- ✅ Generar toda la aplicación desde el JDL
- ✅ Instalar dependencias

### Opción 2: Manual

#### Paso 1: Instalar JHipster
```bash
npm install -g generator-jhipster@latest
```

#### Paso 2: Generar el Proyecto
```bash
cd venta-cafe-jhipster
jhipster import-jdl venta-cafe.jdl --force --with-admin-ui
```

#### Paso 3: Instalar Dependencias (si es necesario)
```bash
npm install
```

## ▶️ Ejecutar la Aplicación

### Opción A: Desarrollo (Recomendado para cambios)

**Terminal 1 - Backend:**
```bash
mvn spring-boot:run
```
Backend escucha en: `http://localhost:8080`

**Terminal 2 - Frontend:**
```bash
npm start
```
Frontend se abre en: `http://localhost:3000`

### Opción B: Docker (Todo integrado)

```bash
# Compilar para producción
mvn package -Pprod

# Ejecutar con Docker Compose
docker-compose up --build
```

Acceso:
- Frontend: `http://localhost:3000`
- Backend: `http://localhost:8080`

## 🔑 Credenciales por Defecto

```
Usuario: admin
Contraseña: admin
```

## 📊 URLs Importantes

| URL | Descripción |
|-----|-------------|
| http://localhost:3000 | Frontend React |
| http://localhost:8080 | Backend Spring Boot |
| http://localhost:8080/swagger-ui/index.html | API Swagger UI |
| http://localhost:8080/management/actuator | Spring Actuator |
| http://localhost:8081 | Adminer (Base de datos) |

## 🔧 Comandos Útiles

### Desarrollo

```bash
# Build del proyecto
mvn clean package

# Solo backend
mvn clean compile

# Solo frontend
npm install && npm run build

# Tests backend
mvn test

# Tests frontend
npm test
```

### Docker

```bash
# Ver logs
docker-compose logs -f

# Ver solo de la API
docker-compose logs -f venta-cafe-api

# Detener servicios
docker-compose down

# Limpiar todo (incluyendo base de datos)
docker-compose down -v
```

### Maven

```bash
# Actualizar dependencias
mvn clean dependency:resolve

# Análisis de código
mvn clean site

# Reportes
mvn checkstyle:checkstyle
mvn findbugs:findbugs
```

## 📁 Estructura Base Generada

```
venta-cafe/
├── src/
│   ├── main/
│   │   ├── java/com/ventacafe/
│   │   │   ├── domain/        # Entidades JPA
│   │   │   ├── repository/    # Spring Data Repos
│   │   │   ├── service/       # Lógica de negocio
│   │   │   ├── web/rest/      # Controladores REST
│   │   │   └── security/      # JWT y autorización
│   │   ├── resources/
│   │   │   └── db/
│   │   │       └── changelog/  # Scripts de BD (Liquibase)
│   │   └── webapp/
│   │       └── app/            # React (TypeScript)
│   └── test/
│       ├── java/               # Tests JUnit
│       └── javascript/         # Tests React
├── .jhipster/                  # Configuración JHipster
├── docker-compose.yml          # Servicios Docker
├── Dockerfile                  # Imagen app
├── pom.xml                     # Maven
├── package.json                # npm
└── venta-cafe.jdl              # Definición del modelo
```

## 🚀 Regenerar Cambios en el JDL

Si modificas `venta-cafe.jdl`:

```bash
jhipster import-jdl venta-cafe.jdl --force
```

Esto:
- ✅ Mantiene cambios personalizados
- ✅ Actualiza entidades
- ✅ Regenera servicios y controladores
- ⚠️ Puede sobrescribir cambios manuales

## 🐛 Troubleshooting

### Error: "jhipster command not found"
```bash
# Reinstalar globalmente
npm install -g generator-jhipster@latest

# O ejecutar con npx
npx jhipster --version
```

### Error: "Port 8080 already in use"
```bash
# Encontrar proceso en puerto 8080
netstat -ano | findstr :8080

# Matar proceso (Windows)
taskkill /PID <PID> /F

# O cambiar puerto en application.yml
server:
  port: 8081
```

### Error: "Cannot connect to database"
```bash
# Verificar PostgreSQL está ejecutándose
docker-compose ps

# Ver logs de BD
docker-compose logs db

# Reiniciar servicios
docker-compose restart
```

### Node modules error
```bash
# Limpiar todo
rm -rf node_modules package-lock.json

# Reinstalar
npm install
```

## 📚 Documentación Oficial

- [JHipster Docs](https://www.jhipster.tech/)
- [JDL Reference](https://www.jhipster.tech/jdl/intro)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [React](https://reactjs.org/)

## 💡 Tips Productivos

1. **Hot Reload**: Los cambios se recargan automáticamente en desarrollo
2. **Swagger UI**: Prueba endpoints en http://localhost:8080/swagger-ui.html
3. **TypeScript**: Frontend usa TypeScript con Types seguros
4. **Spring DevTools**: Backend se recarga automáticamente
5. **Redux DevTools**: Instala extensión de Chrome para debugging

## ✅ Checklist de Inicio

- [ ] JHipster instalado (`jhipster --version`)
- [ ] Node.js 18+ instalado (`node --version`)
- [ ] Maven instalado (`mvn --version`)
- [ ] PostgreSQL ejecutándose (si no usas Docker)
- [ ] Ejecutar script `generate.bat` o `generate.sh`
- [ ] Esperar a que termine la generación
- [ ] Abrir 2 terminales para backend y frontend
- [ ] Terminal 1: `mvn spring-boot:run`
- [ ] Terminal 2: `npm start`
- [ ] Acceder a http://localhost:3000
- [ ] Login con admin/admin

## 🎉 ¡Listo!

Ya tienes un proyecto profesional de gestión de café con:
- ✅ Backend REST completo
- ✅ Frontend React moderno
- ✅ Base de datos PostgreSQL
- ✅ Autenticación JWT
- ✅ Auditoría automática
- ✅ Documentación Swagger

---

**¿Preguntas?** Revisa la [documentación oficial de JHipster](https://www.jhipster.tech/)
