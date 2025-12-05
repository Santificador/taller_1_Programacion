BUILD SUCCESS@echo off
REM Configurar PATH para Node.js
set PATH=C:\Program Files\nodejs;%PATH%

echo === Instalando JHipster ===
cmd /c npm install -g generator-jhipster@latest

echo === JHipster Versión ===
cmd /c jhipster --version

echo === Generando proyecto desde JDL ===
cd /d c:\Users\Pau\OneDrive\Escritorio\Presi\Examen_final_taller_4\venta-cafe-jhipster
cmd /c jhipster import-jdl venta-cafe.jdl --force --with-admin-ui

echo === Completado ===
pause
