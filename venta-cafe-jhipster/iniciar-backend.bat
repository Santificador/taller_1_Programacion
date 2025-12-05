@echo off
setlocal enabledelayedexpansion

cd /d "C:\Users\Pau\OneDrive\Escritorio\Presi\Examen_final_taller_4\venta-cafe-jhipster"

echo.
echo ============================================
echo   INICIANDO BACKEND (Spring Boot)
echo ============================================
echo.
echo Accede a:
echo   API: http://localhost:8080
echo   Swagger: http://localhost:8080/swagger-ui/
echo.
echo Presiona Ctrl+C para detener el servidor
echo.

call mvn spring-boot:run

