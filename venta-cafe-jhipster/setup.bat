@echo off
setlocal enabledelayedexpansion

echo Buscando Node.js...

REM Buscar Node.js en ubicaciones estándar
if exist "C:\Program Files\nodejs\npm.cmd" (
    echo Encontrado Node.js en Program Files
    set PATH=C:\Program Files\nodejs;!PATH!
) else if exist "C:\Program Files (x86)\nodejs\npm.cmd" (
    echo Encontrado Node.js en Program Files (x86)
    set PATH=C:\Program Files (x86)\nodejs;!PATH!
)

REM Mostrar versión
echo.
node --version
npm --version

echo.
echo Verificando si JHipster está instalado...
where jhipster

if !errorlevel! neq 0 (
    echo JHipster no encontrado. Instalando...
    call npm install -g generator-jhipster@latest
) else (
    echo JHipster ya está instalado.
)

echo.
echo Iniciando generación del proyecto...
call jhipster import-jdl venta-cafe.jdl --force --with-admin-ui

pause
