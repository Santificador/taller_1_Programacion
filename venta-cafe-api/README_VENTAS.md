# 📋 Proceso: Gestión de Ventas

Breve instructivo para probar el módulo de ventas (ejemplo mínimo).

## Endpoints principales

- POST /api/ventas → Registrar venta
- GET  /api/ventas → Listar ventas
- GET  /api/ventas/{id} → Obtener venta por id

## Cómo probar

1. Levanta el proyecto Quarkus:

```bash
cd venta-cafe-api
mvn quarkus:dev
```

2. Ejemplo request (registrar venta)

POST http://localhost:8080/api/ventas

Body (JSON):

```json
{
  "idCliente": 1,
  "items": [
    { "producto": "Cafe A", "cantidad": 2, "precioUnitario": 3.5 },
    { "producto": "Cafe B", "cantidad": 1, "precioUnitario": 5.0 }
  ]
}
```

Response (201):

```json
{
  "id": 10,
  "cliente": "Juan Perez",
  "total": 12.0,
  "fecha": "2025-12-15",
  "items": [ ... ]
}
```

3. Obtener venta por id:

GET http://localhost:8080/api/ventas/10

4. OpenAPI / Swagger UI en:

http://localhost:8080/q/swagger-ui

---

Capturas y colección Postman: `postman/ventas.postman_collection.json`

## Notas sobre pruebas

- Las pruebas unitarias (por ejemplo `VentaServicioTest`) pueden ejecutarse con Maven sin necesidad de base de datos.
- Las pruebas de integración (`@QuarkusTest`) arrancan Quarkus y requieren la configuración de base de datos en `application.properties` o variables de entorno (Postgres). Para ejecutar todas las pruebas en local, configura las credenciales de la base de datos, por ejemplo:

```bash
mvn test -Dquarkus.datasource.username=postgres -Dquarkus.datasource.password=postgres
```

Si prefieres evitar configuración, ejecuta pruebas unitarias específicas:

```bash
mvn -Dtest=VentaServicioTest test
```
