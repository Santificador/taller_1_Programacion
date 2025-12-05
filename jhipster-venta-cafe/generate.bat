@echo off
REM Script para generar proyecto JHipster desde JDL
REM Requiere: Node.js, npm y JHipster CLI instalados

echo ============================================
echo  Generando Proyecto JHipster - Venta Cafe
echo ============================================
echo.

echo Verificando que JHipster CLI esta instalado...
call npm list -g generator-jhipster
if errorlevel 1 (
    echo.
    echo [!] JHipster CLI no esta instalado
    echo Instalando JHipster CLI globalmente...
    call npm install -g generator-jhipster
)

echo.
echo Limpiando generaciones anteriores...
if exist ".jhipster" rmdir /s /q ".jhipster" 2>nul
if exist "node_modules" rmdir /s /q "node_modules" 2>nul
if exist "src" rmdir /s /q "src" 2>nul
if exist ".angular-cli.json" del ".angular-cli.json" 2>nul
if exist "angular.json" del "angular.json" 2>nul
if exist "package.json" del "package.json" 2>nul
if exist "pom.xml" del "pom.xml" 2>nul

echo.
echo Generando aplicacion desde JDL...
call jhipster import-jdl venta-cafe.jdl --force --no-skip-server --no-skip-client

echo.
echo ============================================
echo  Generacion completada!
echo ============================================
echo.
echo Proximos pasos:
echo 1. cd al directorio del proyecto
echo 2. Ejecutar: npm install
echo 3. Ejecutar: ./mvnw (Windows: mvnw.cmd)
echo.
pause
