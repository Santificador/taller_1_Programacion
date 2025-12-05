@echo off
REM 🚀 Script de Generación Automática del Proyecto JHipster para Windows
REM Ejecuta este script para generar automáticamente el proyecto desde el JDL

setlocal enabledelayedexpansion

echo.
echo ╔════════════════════════════════════════════════════════════╗
echo ║   Sistema de Venta de Café - Generación JHipster          ║
echo ╚════════════════════════════════════════════════════════════╝
echo.

REM Buscar Node.js en ubicaciones estándar
set NODE_PATH=
if exist "C:\Program Files\nodejs\npm.cmd" (
    set NODE_PATH=C:\Program Files\nodejs
) else if exist "C:\Program Files (x86)\nodejs\npm.cmd" (
    set NODE_PATH=C:\Program Files (x86)\nodejs
)

if defined NODE_PATH (
    echo ✅ Node.js encontrado en: !NODE_PATH!
    set PATH=!NODE_PATH!;!PATH!
) else (
    echo ❌ Node.js no encontrado en ubicaciones estándar
    echo 📥 Por favor instala Node.js desde: https://nodejs.org/
    pause
    exit /b 1
)

REM Verificar si JHipster está instalado
where jhipster >nul 2>nul
if !errorlevel! neq 0 (
    echo ❌ JHipster no está instalado globalmente.
    echo 📦 Instalando JHipster globalmente...
    call npm install -g generator-jhipster@latest
)

REM Verificar Node.js
where node >nul 2>nul
if !errorlevel! neq 0 (
    echo ❌ Node.js no está instalado.
    echo 📥 Por favor descarga e instala desde: https://nodejs.org/
    pause
    exit /b 1
)

REM Verificar Maven
where mvn >nul 2>nul
if !errorlevel! neq 0 (
    echo ❌ Maven no está instalado.
    echo 📥 Por favor descarga e instala desde: https://maven.apache.org/
    pause
    exit /b 1
)

REM Mostrar versiones
echo ✅ Herramientas verificadas:
for /f "tokens=*" %%i in ('node --version') do echo    - Node.js: %%i
for /f "tokens=*" %%i in ('npm --version') do echo    - npm: %%i
for /f "tokens=*" %%i in ('jhipster --version') do echo    - JHipster: %%i
for /f "tokens=*" %%i in ('mvn --version') do (
    echo    - Maven: %%i
    goto :maven_ok
)
:maven_ok
echo.

REM Crear directorio del proyecto si no existe
if not exist "venta-cafe" (
    echo 📁 Creando directorio del proyecto...
    mkdir venta-cafe
)

cd venta-cafe

echo 📋 Generando proyecto desde JDL...
echo    Esto puede tomar algunos minutos...
echo.

REM Generar el proyecto desde el JDL
call jhipster import-jdl ..\venta-cafe.jdl --force --with-admin-ui

echo.
echo ✅ ¡Proyecto generado exitosamente!
echo.
echo ╔════════════════════════════════════════════════════════════╗
echo ║   Próximos Pasos:                                         ║
echo ╠════════════════════════════════════════════════════════════╣
echo ║                                                            ║
echo ║  1. Instalar dependencias (si es necesario):              ║
echo ║     npm install                                           ║
echo ║                                                            ║
echo ║  2. Ejecutar en desarrollo (dos terminales):              ║
echo ║     Terminal 1: mvn spring-boot:run                       ║
echo ║     Terminal 2: npm start                                 ║
echo ║                                                            ║
echo ║  3. O ejecutar con Docker:                                ║
echo ║     docker-compose up --build                             ║
echo ║                                                            ║
echo ║  4. Acceder a la aplicación:                              ║
echo ║     - Frontend: http://localhost:3000                     ║
echo ║     - Backend:  http://localhost:8080                     ║
echo ║     - Swagger:  http://localhost:8080/swagger-ui.html     ║
echo ║                                                            ║
echo ║  5. Login con:                                            ║
echo ║     Usuario: admin                                        ║
echo ║     Contraseña: admin                                     ║
echo ║                                                            ║
echo ╚════════════════════════════════════════════════════════════╝
echo.

pause
