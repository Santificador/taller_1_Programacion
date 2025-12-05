@echo off
REM Script para ejecutar el backend con Maven

cd /d "C:\Users\Pau\OneDrive\Escritorio\Presi\Examen_final_taller_4\venta-cafe-jhipster"

echo Iniciando backend (Spring Boot)...
echo Accede a: http://localhost:8080
echo Swagger UI: http://localhost:8080/swagger-ui/
echo.

mvn spring-boot:run

pause
