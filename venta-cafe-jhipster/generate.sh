#!/bin/bash

# 🚀 Script de Generación Automática del Proyecto JHipster
# Ejecuta este script para generar automáticamente el proyecto desde el JDL

set -e

echo "╔════════════════════════════════════════════════════════════╗"
echo "║   Sistema de Venta de Café - Generación JHipster          ║"
echo "╚════════════════════════════════════════════════════════════╝"
echo ""

# Verificar si JHipster está instalado
if ! command -v jhipster &> /dev/null; then
    echo "❌ JHipster no está instalado."
    echo "📦 Instalando JHipster globalmente..."
    npm install -g generator-jhipster@latest
fi

# Verificar Node.js
if ! command -v node &> /dev/null; then
    echo "❌ Node.js no está instalado. Por favor instálalo desde https://nodejs.org/"
    exit 1
fi

# Verificar Maven
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven no está instalado. Por favor instálalo desde https://maven.apache.org/"
    exit 1
fi

echo "✅ Herramientas verificadas:"
echo "   - Node.js: $(node --version)"
echo "   - npm: $(npm --version)"
echo "   - JHipster: $(jhipster --version)"
echo "   - Maven: $(mvn --version | head -1)"
echo ""

# Crear directorio del proyecto si no existe
if [ ! -d "venta-cafe" ]; then
    echo "📁 Creando directorio del proyecto..."
    mkdir -p venta-cafe
    cd venta-cafe
else
    cd venta-cafe
fi

echo "📋 Generando proyecto desde JDL..."
echo "   Esto puede tomar algunos minutos..."
echo ""

# Generar el proyecto desde el JDL
jhipster import-jdl ../venta-cafe.jdl --force --with-admin-ui

echo ""
echo "✅ ¡Proyecto generado exitosamente!"
echo ""
echo "╔════════════════════════════════════════════════════════════╗"
echo "║   Próximos Pasos:                                         ║"
echo "╠════════════════════════════════════════════════════════════╣"
echo "║                                                            ║"
echo "║  1. Instalar dependencias (si es necesario):              ║"
echo "║     npm install                                           ║"
echo "║                                                            ║"
echo "║  2. Ejecutar en desarrollo (dos terminales):              ║"
echo "║     Terminal 1: mvn spring-boot:run                       ║"
echo "║     Terminal 2: npm start                                 ║"
echo "║                                                            ║"
echo "║  3. O ejecutar con Docker:                                ║"
echo "║     docker-compose up --build                             ║"
echo "║                                                            ║"
echo "║  4. Acceder a la aplicación:                              ║"
echo "║     - Frontend: http://localhost:3000                     ║"
echo "║     - Backend:  http://localhost:8080                     ║"
echo "║     - Swagger:  http://localhost:8080/swagger-ui.html     ║"
echo "║                                                            ║"
echo "║  5. Login con:                                            ║"
echo "║     Usuario: admin                                        ║"
echo "║     Contraseña: admin                                     ║"
echo "║                                                            ║"
echo "╚════════════════════════════════════════════════════════════╝"
echo ""
