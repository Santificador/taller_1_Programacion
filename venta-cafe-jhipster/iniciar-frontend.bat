@echo off
setlocal enabledelayedexpansion

cd /d "C:\Users\Pau\OneDrive\Escritorio\Presi\Examen_final_taller_4\venta-cafe-jhipster"

echo.
echo ============================================
echo   INICIANDO FRONTEND (Angular - npm start)
echo ============================================
echo.
echo Accede a: http://localhost:4200
echo.
echo Presiona Ctrl+C para detener
echo.

REM Intentar usar npm del PATH global
where npm >nul 2>&1
if %errorlevel% equ 0 (
    echo Usando npm global...
    call npm start
) else (
    echo npm no encontrado en PATH
    echo Intenta abrir PowerShell como Administrador y ejecutar:
    echo npm start
    pause
)

