const express = require('express');
const path = require('path');

const app = express();
const port = 8080;
const staticPath = 'C:\\Users\\Pau\\OneDrive\\Escritorio\\Presi\\taller_1_entrega_final\\venta-cafe-jhipster\\target\\classes\\static';

// Servir archivos estáticos
app.use(express.static(staticPath));

// Redirigir todas las rutas no encontradas a index.html (para Angular routing)
app.use((req, res) => {
  res.sendFile(path.join(staticPath, 'index.html'));
});

app.listen(port, () => {
  console.log(`✅ Frontend corriendo en http://localhost:${port}`);
});
