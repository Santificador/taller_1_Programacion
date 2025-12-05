# Script para generar proyecto JHipster
# Este script verifica requisitos, instala JHipster CLI si es necesario, y genera el proyecto

Write-Host "`n" -ForegroundColor Cyan
Write-Host "╔════════════════════════════════════════════════════╗" -ForegroundColor Cyan
Write-Host "║   Generador Automático de Proyecto JHipster       ║" -ForegroundColor Cyan
Write-Host "║   Sistema de Venta de Café - Versión Robusta      ║" -ForegroundColor Cyan
Write-Host "╚════════════════════════════════════════════════════╝" -ForegroundColor Cyan
Write-Host "`n"

# Función para verificar instalación
function Test-CommandExists {
    param($command)
    $null = Get-Command $command -ErrorAction SilentlyContinue
    return $?
}

# Verificar Node.js
Write-Host "🔍 Verificando requisitos previos..." -ForegroundColor Yellow
Write-Host ""

if (Test-CommandExists node) {
    $nodeVersion = node --version
    Write-Host "✅ Node.js instalado: $nodeVersion" -ForegroundColor Green
} else {
    Write-Host "❌ Node.js NO está instalado" -ForegroundColor Red
    Write-Host "   Descarga desde: https://nodejs.org/" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "   Pasos:" -ForegroundColor Cyan
    Write-Host "   1. Descargar Node.js LTS" -ForegroundColor White
    Write-Host "   2. Ejecutar el instalador" -ForegroundColor White
    Write-Host "   3. Reiniciar PowerShell" -ForegroundColor White
    Write-Host "   4. Volver a ejecutar este script" -ForegroundColor White
    Write-Host ""
    exit 1
}

# Verificar npm
if (Test-CommandExists npm) {
    $npmVersion = npm --version
    Write-Host "✅ npm instalado: $npmVersion" -ForegroundColor Green
} else {
    Write-Host "❌ npm NO está instalado (debe venir con Node.js)" -ForegroundColor Red
    exit 1
}

# Verificar Java
if (Test-CommandExists java) {
    $javaVersion = java -version 2>&1 | Select-String "version"
    Write-Host "✅ Java instalado: $javaVersion" -ForegroundColor Green
} else {
    Write-Host "❌ Java NO está instalado" -ForegroundColor Red
    Write-Host "   Descargar desde: https://www.oracle.com/java/technologies/downloads/" -ForegroundColor Yellow
    exit 1
}

Write-Host ""
Write-Host "📦 Verificando JHipster CLI..." -ForegroundColor Yellow
Write-Host ""

if (Test-CommandExists jhipster) {
    $jhipsterVersion = jhipster --version
    Write-Host "✅ JHipster CLI instalado: $jhipsterVersion" -ForegroundColor Green
} else {
    Write-Host "⏳ JHipster CLI no está instalado" -ForegroundColor Yellow
    Write-Host "   Instalando JHipster CLI globalmente..." -ForegroundColor Cyan
    Write-Host ""
    
    npm install -g generator-jhipster
    
    if (Test-CommandExists jhipster) {
        Write-Host ""
        Write-Host "✅ JHipster CLI instalado exitosamente" -ForegroundColor Green
    } else {
        Write-Host "❌ Error al instalar JHipster CLI" -ForegroundColor Red
        exit 1
    }
}

Write-Host ""
Write-Host "🗑️  Limpiando generaciones anteriores..." -ForegroundColor Yellow

Remove-Item -Path ".jhipster", "node_modules", "src", ".angular-cli.json", "angular.json", "package.json", "pom.xml", "package-lock.json" -Recurse -Force -ErrorAction SilentlyContinue

Write-Host "✅ Limpieza completada" -ForegroundColor Green
Write-Host ""

Write-Host "🎯 Generando proyecto desde JDL..." -ForegroundColor Cyan
Write-Host "   Archivo: venta-cafe.jdl" -ForegroundColor White
Write-Host ""
Write-Host "   Esto puede tomar 2-5 minutos..." -ForegroundColor Yellow
Write-Host "   Por favor, espera..." -ForegroundColor Yellow
Write-Host ""

$startTime = Get-Date

jhipster import-jdl venta-cafe.jdl --force --no-skip-server --no-skip-client

if ($LASTEXITCODE -eq 0) {
    $endTime = Get-Date
    $duration = ($endTime - $startTime).TotalSeconds
    
    Write-Host ""
    Write-Host "╔════════════════════════════════════════════════════╗" -ForegroundColor Green
    Write-Host "║   ✅ PROYECTO GENERADO EXITOSAMENTE               ║" -ForegroundColor Green
    Write-Host "╚════════════════════════════════════════════════════╝" -ForegroundColor Green
    Write-Host ""
    Write-Host "⏱️  Tiempo de generación: $([Math]::Round($duration, 2)) segundos" -ForegroundColor Cyan
    Write-Host ""
    
    Write-Host "📦 Instalando dependencias Node.js..." -ForegroundColor Yellow
    npm install
    
    Write-Host ""
    Write-Host "╔════════════════════════════════════════════════════╗" -ForegroundColor Green
    Write-Host "║   🎉 ¡LISTO PARA EJECUTAR!                         ║" -ForegroundColor Green
    Write-Host "╚════════════════════════════════════════════════════╝" -ForegroundColor Green
    Write-Host ""
    
    Write-Host "📝 Próximos pasos:" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "   1️⃣  Asegúrate que PostgreSQL esté ejecutándose:" -ForegroundColor White
    Write-Host "       docker-compose -f docker-compose.yml up -d" -ForegroundColor Gray
    Write-Host ""
    Write-Host "   2️⃣  Ejecutar la aplicación:" -ForegroundColor White
    Write-Host "       npm start" -ForegroundColor Gray
    Write-Host ""
    Write-Host "   3️⃣  Abrir en navegador:" -ForegroundColor White
    Write-Host "       http://localhost:4200" -ForegroundColor Gray
    Write-Host ""
    Write-Host "   4️⃣  Iniciar sesión con:" -ForegroundColor White
    Write-Host "       Usuario: admin" -ForegroundColor Gray
    Write-Host "       Contraseña: admin" -ForegroundColor Gray
    Write-Host ""
    
    Write-Host "📚 Documentación:" -ForegroundColor Cyan
    Write-Host "   JHipster: https://www.jhipster.tech/" -ForegroundColor Gray
    Write-Host "   Angular: https://angular.io/" -ForegroundColor Gray
    Write-Host "   Spring Boot: https://spring.io/" -ForegroundColor Gray
    Write-Host ""
    
} else {
    Write-Host ""
    Write-Host "❌ Error al generar el proyecto" -ForegroundColor Red
    Write-Host "   Verifica el mensaje de error anterior" -ForegroundColor Yellow
    exit 1
}
