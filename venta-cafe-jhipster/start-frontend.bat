@echo off
REM Script para ejecutar npm start usando la ruta local instalada por Maven

cd /d "C:\Users\Pau\OneDrive\Escritorio\Presi\Examen_final_taller_4\venta-cafe-jhipster"

echo Iniciando frontend con npm start...
echo.

REM Usar npm local que Maven instaló
call "C:\Users\Pau\.m2\repository\com\github\eirslett\npm\11.3.0\npm.cmd" start

pause
