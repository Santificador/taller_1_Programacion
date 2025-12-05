# 🐳 Guía de Despliegue con Docker

## 📋 Requisitos Previos

- Docker Desktop instalado y ejecutándose
- Docker Compose v2.0 o superior
- Al menos 2GB de RAM disponible

## 🚀 Instrucciones de Despliegue

### 1. Construir la Aplicación

Primero, compila la aplicación con Maven:

```powershell
cd venta-cafe-api
mvn clean package -DskipTests
```

### 2. Construir y Levantar los Contenedores

Ejecuta Docker Compose para construir y levantar todos los servicios:

```powershell
docker-compose up --build
```

O en modo detached (segundo plano):

```powershell
docker-compose up -d --build
```

### 3. Acceder a la Aplicación

Una vez que los contenedores estén ejecutándose:

- **Interfaz Web**: http://localhost:8080/index.html
- **Swagger UI**: http://localhost:8080/q/swagger-ui
- **API REST**: http://localhost:8080/api/
- **Adminer (DB Manager)**: http://localhost:8081

#### Credenciales de Adminer:
- **Sistema**: PostgreSQL
- **Servidor**: db
- **Usuario**: admin
- **Contraseña**: admin
- **Base de datos**: ventacafe

## 📦 Servicios Incluidos

### 1. PostgreSQL Database (db)
- Puerto: 5432
- Base de datos: ventacafe
- Usuario/Contraseña: admin/admin
- Volumen persistente para los datos

### 2. Venta Café API (venta-cafe-api)
- Puerto: 8080
- Interfaz web incluida
- API REST completa
- Swagger UI para documentación

### 3. Adminer
- Puerto: 8081
- Interfaz web para administrar PostgreSQL

## 🔧 Comandos Útiles

### Ver logs de los contenedores
```powershell
# Todos los servicios
docker-compose logs -f

# Solo la API
docker-compose logs -f venta-cafe-api

# Solo la base de datos
docker-compose logs -f db
```

### Detener los contenedores
```powershell
docker-compose down
```

### Detener y eliminar volúmenes (limpia la BD)
```powershell
docker-compose down -v
```

### Reconstruir solo la API
```powershell
docker-compose up -d --build venta-cafe-api
```

### Ver el estado de los contenedores
```powershell
docker-compose ps
```

### Reiniciar un servicio
```powershell
docker-compose restart venta-cafe-api
```

## 🔄 Actualizar la Aplicación

Si haces cambios en el código:

1. Detén los contenedores:
   ```powershell
   docker-compose down
   ```

2. Recompila la aplicación:
   ```powershell
   mvn clean package -DskipTests
   ```

3. Reconstruye y levanta los contenedores:
   ```powershell
   docker-compose up --build -d
   ```

## 🗄️ Datos Iniciales

La aplicación carga automáticamente datos de prueba desde `import.sql`:
- 4 Usuarios
- 8 Clientes
- 8 Tipos de Café
- 10 Ventas de ejemplo

## 🌐 Red de Docker

Todos los servicios están en la red `venta-cafe-network`, lo que permite la comunicación entre contenedores.

## 📊 Verificar que Todo Funciona

1. Verifica que todos los contenedores estén corriendo:
   ```powershell
   docker-compose ps
   ```
   
   Deberías ver 3 servicios en estado "Up" o "running"

2. Verifica la salud de PostgreSQL:
   ```powershell
   docker-compose exec db pg_isready -U admin -d ventacafe
   ```

3. Prueba la API:
   ```powershell
   curl http://localhost:8080/api/clientes
   ```

4. Abre la interfaz web:
   - Navegador → http://localhost:8080/index.html

## 🐛 Solución de Problemas

### La API no se conecta a la base de datos

Verifica los logs:
```powershell
docker-compose logs db
docker-compose logs venta-cafe-api
```

Asegúrate de que PostgreSQL esté completamente iniciado antes que la API (el healthcheck debería manejarlo).

### Puerto 8080 ya en uso

Si tienes la aplicación corriendo localmente, detenla primero:
```powershell
# Encuentra el proceso usando el puerto 8080
netstat -ano | findstr :8080

# Mata el proceso (reemplaza PID con el número del proceso)
taskkill /PID <PID> /F
```

O cambia el puerto en docker-compose.yml:
```yaml
ports:
  - "8090:8080"  # Ahora la app estará en http://localhost:8090
```

### Errores de compilación

Asegúrate de tener Java 17 instalado:
```powershell
java -version
```

Limpia el proyecto Maven:
```powershell
mvn clean
```

### La base de datos pierde los datos

Los datos se guardan en un volumen Docker llamado `postgres_data`. 
Para mantener los datos, NO uses el flag `-v` al hacer `docker-compose down`.

## 🔐 Producción

Para producción, considera:

1. Cambiar las credenciales de la base de datos
2. Usar variables de entorno para secretos
3. Configurar SSL/TLS
4. Ajustar los recursos de memoria
5. Implementar backups de la base de datos

## 📝 Estructura de Archivos Docker

```
venta-cafe-api/
├── Dockerfile                 # Imagen de la aplicación
├── docker-compose.yml         # Orquestación de servicios
├── .dockerignore             # Archivos a ignorar en la construcción
└── src/
    └── main/
        └── resources/
            └── META-INF/
                └── resources/
                    ├── index.html    # Interfaz web
                    └── app.js        # Lógica de la interfaz
```

## ✅ Checklist de Despliegue

- [ ] Docker Desktop está ejecutándose
- [ ] Proyecto compilado con `mvn package`
- [ ] Puerto 8080 disponible
- [ ] Puerto 5432 disponible
- [ ] Puerto 8081 disponible
- [ ] Ejecutar `docker-compose up --build`
- [ ] Verificar que los 3 servicios estén "Up"
- [ ] Acceder a http://localhost:8080/index.html
- [ ] Probar crear una venta
- [ ] Verificar auditoría

---

**¡La aplicación completa con interfaz web está lista en Docker! 🎉**
