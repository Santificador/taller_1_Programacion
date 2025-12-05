// Configuración de la API
const API_BASE_URL = 'http://localhost:8080/api';

// Estado global
let clientes = [];
let cafes = [];
let ventas = [];
let auditorias = [];

// Inicialización
document.addEventListener('DOMContentLoaded', () => {
    inicializarApp();
    configurarEventos();
});

async function inicializarApp() {
    // Establecer fecha actual
    document.getElementById('fecha').valueAsDate = new Date();
    
    // Cargar datos iniciales
    await Promise.all([
        cargarClientes(),
        cargarCafes(),
        cargarVentas(),
        cargarAuditorias()
    ]);
    
    // Cargar estadísticas
    cargarEstadisticas();
}

function configurarEventos() {
    // Evento submit del formulario
    document.getElementById('ventaForm').addEventListener('submit', registrarVenta);
    
    // Calcular total al cambiar cantidad o café
    document.getElementById('cantidad').addEventListener('input', calcularTotal);
    document.getElementById('cafe').addEventListener('change', calcularTotal);
}

// ============ GESTIÓN DE TABS ============
function showTab(tabId) {
    // Ocultar todos los tabs
    document.querySelectorAll('.tab-content').forEach(tab => {
        tab.classList.remove('active');
    });
    
    // Remover clase active de todos los botones
    document.querySelectorAll('.nav-tabs button').forEach(btn => {
        btn.classList.remove('active');
    });
    
    // Mostrar tab seleccionado
    document.getElementById(tabId).classList.add('active');
    
    // Activar botón correspondiente
    event.target.classList.add('active');
    
    // Recargar datos según el tab
    if (tabId === 'listar-ventas') {
        cargarVentas();
    } else if (tabId === 'auditoria') {
        cargarAuditorias();
    } else if (tabId === 'estadisticas') {
        cargarEstadisticas();
    }
}

// ============ CLIENTES ============
async function cargarClientes() {
    try {
        const response = await fetch(`${API_BASE_URL}/clientes?size=100`);
        clientes = await response.json();
        
        const select = document.getElementById('cliente');
        select.innerHTML = '<option value="">Seleccione un cliente...</option>';
        
        clientes.forEach(cliente => {
            const option = document.createElement('option');
            option.value = cliente.id_cliente;
            option.textContent = `${cliente.nombre} ${cliente.apellido}`;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Error al cargar clientes:', error);
        mostrarAlerta('venta-alert', 'Error al cargar clientes', 'error');
    }
}

// ============ CAFÉS ============
async function cargarCafes() {
    try {
        const response = await fetch(`${API_BASE_URL}/cafes?size=100`);
        cafes = await response.json();
        
        const select = document.getElementById('cafe');
        select.innerHTML = '<option value="">Seleccione un café...</option>';
        
        cafes.forEach(cafe => {
            const option = document.createElement('option');
            option.value = cafe.id_cafe;
            option.textContent = `${cafe.nombre} - $${cafe.precio.toFixed(2)}`;
            option.dataset.precio = cafe.precio;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Error al cargar cafés:', error);
        mostrarAlerta('venta-alert', 'Error al cargar cafés', 'error');
    }
}

// ============ VENTAS ============
function calcularTotal() {
    const cafeSelect = document.getElementById('cafe');
    const cantidadInput = document.getElementById('cantidad');
    const precioInput = document.getElementById('precioUnitario');
    const totalInput = document.getElementById('montoTotal');
    
    const selectedOption = cafeSelect.options[cafeSelect.selectedIndex];
    const precio = parseFloat(selectedOption.dataset.precio) || 0;
    const cantidad = parseInt(cantidadInput.value) || 0;
    
    precioInput.value = precio.toFixed(2);
    totalInput.value = (precio * cantidad).toFixed(2);
}

async function registrarVenta(event) {
    event.preventDefault();
    
    const clienteId = document.getElementById('cliente').value;
    const cafeId = document.getElementById('cafe').value;
    const cantidad = parseInt(document.getElementById('cantidad').value);
    const fecha = document.getElementById('fecha').value;
    const usuario = document.getElementById('usuario').value;
    const precioUnitario = parseFloat(document.getElementById('precioUnitario').value);
    const montoTotal = parseFloat(document.getElementById('montoTotal').value);
    
    const venta = {
        cliente: { id_cliente: parseInt(clienteId) },
        cafe: { id_cafe: parseInt(cafeId) },
        cantidad: cantidad,
        precioUnitario: precioUnitario,
        montoTotal: montoTotal,
        fecha: fecha
    };
    
    try {
        const response = await fetch(`${API_BASE_URL}/ventas`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-Usuario': usuario
            },
            body: JSON.stringify(venta)
        });
        
        if (response.ok) {
            mostrarAlerta('venta-alert', '✅ Venta registrada exitosamente', 'success');
            document.getElementById('ventaForm').reset();
            document.getElementById('fecha').valueAsDate = new Date();
            document.getElementById('precioUnitario').value = '';
            document.getElementById('montoTotal').value = '';
            await cargarVentas();
        } else {
            throw new Error('Error al registrar venta');
        }
    } catch (error) {
        console.error('Error:', error);
        mostrarAlerta('venta-alert', '❌ Error al registrar la venta', 'error');
    }
}

async function cargarVentas() {
    const container = document.getElementById('ventasTable');
    container.innerHTML = '<div class="loading">Cargando ventas...</div>';
    
    try {
        const response = await fetch(`${API_BASE_URL}/ventas?size=100`);
        ventas = await response.json();
        
        if (ventas.length === 0) {
            container.innerHTML = '<div class="alert alert-info">No hay ventas registradas</div>';
            return;
        }
        
        let html = `
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Cliente</th>
                        <th>Café</th>
                        <th>Cantidad</th>
                        <th>Precio Unit.</th>
                        <th>Total</th>
                        <th>Fecha</th>
                        <th>Creado Por</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
        `;
        
        ventas.forEach(venta => {
            const clienteNombre = venta.cliente ? 
                `${venta.cliente.nombre} ${venta.cliente.apellido}` : 'N/A';
            const cafeNombre = venta.cafe ? venta.cafe.nombre : 'N/A';
            
            html += `
                <tr>
                    <td>${venta.id}</td>
                    <td>${clienteNombre}</td>
                    <td>${cafeNombre}</td>
                    <td>${venta.cantidad}</td>
                    <td>$${venta.precioUnitario.toFixed(2)}</td>
                    <td>$${venta.montoTotal.toFixed(2)}</td>
                    <td>${formatDate(venta.fecha)}</td>
                    <td>${venta.creadoPor || 'Sistema'}</td>
                    <td>
                        <button onclick="eliminarVenta(${venta.id})" class="btn btn-danger" style="padding: 5px 10px; font-size: 12px;">
                            🗑️ Eliminar
                        </button>
                    </td>
                </tr>
            `;
        });
        
        html += '</tbody></table>';
        container.innerHTML = html;
    } catch (error) {
        console.error('Error al cargar ventas:', error);
        container.innerHTML = '<div class="alert alert-error">Error al cargar ventas</div>';
    }
}

async function eliminarVenta(id) {
    if (!confirm('¿Está seguro de eliminar esta venta?')) {
        return;
    }
    
    const usuario = prompt('Ingrese su nombre de usuario:');
    if (!usuario) {
        return;
    }
    
    try {
        const response = await fetch(`${API_BASE_URL}/ventas/${id}`, {
            method: 'DELETE',
            headers: {
                'X-Usuario': usuario
            }
        });
        
        if (response.ok) {
            mostrarAlerta('ventas-alert', '✅ Venta eliminada exitosamente', 'success');
            await cargarVentas();
            await cargarAuditorias();
        } else {
            throw new Error('Error al eliminar venta');
        }
    } catch (error) {
        console.error('Error:', error);
        mostrarAlerta('ventas-alert', '❌ Error al eliminar la venta', 'error');
    }
}

// ============ AUDITORÍA ============
async function cargarAuditorias() {
    const container = document.getElementById('auditoriaTable');
    container.innerHTML = '<div class="loading">Cargando auditorías...</div>';
    
    try {
        const response = await fetch(`${API_BASE_URL}/auditorias?size=100`);
        auditorias = await response.json();
        
        if (auditorias.length === 0) {
            container.innerHTML = '<div class="alert alert-info">No hay registros de auditoría</div>';
            return;
        }
        
        let html = `
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Entidad</th>
                        <th>Acción</th>
                        <th>Usuario</th>
                        <th>Fecha y Hora</th>
                        <th>ID Registro</th>
                        <th>Detalles</th>
                    </tr>
                </thead>
                <tbody>
        `;
        
        auditorias.forEach(auditoria => {
            const badge = getBadgeClass(auditoria.accion);
            html += `
                <tr>
                    <td>${auditoria.id}</td>
                    <td>${auditoria.nombreEntidad}</td>
                    <td><span class="badge ${badge}">${auditoria.accion}</span></td>
                    <td>${auditoria.usuario}</td>
                    <td>${formatDateTime(auditoria.fechaHora)}</td>
                    <td>${auditoria.idRegistro || 'N/A'}</td>
                    <td>${auditoria.detalles || 'Sin detalles'}</td>
                </tr>
            `;
        });
        
        html += '</tbody></table>';
        container.innerHTML = html;
    } catch (error) {
        console.error('Error al cargar auditorías:', error);
        container.innerHTML = '<div class="alert alert-error">Error al cargar auditorías</div>';
    }
}

// ============ ESTADÍSTICAS ============
async function cargarEstadisticas() {
    const container = document.getElementById('statsGrid');
    
    try {
        // Cargar todos los datos
        await Promise.all([
            cargarVentas(),
            cargarAuditorias(),
            cargarClientes(),
            cargarCafes()
        ]);
        
        // Calcular estadísticas
        const totalVentas = ventas.length;
        const totalClientes = clientes.length;
        const totalCafes = cafes.length;
        const totalAuditorias = auditorias.length;
        
        const montoTotalVentas = ventas.reduce((sum, venta) => sum + venta.montoTotal, 0);
        const promedioVenta = totalVentas > 0 ? montoTotalVentas / totalVentas : 0;
        
        const html = `
            <div class="stat-card">
                <h3>${totalVentas}</h3>
                <p>Total de Ventas</p>
            </div>
            <div class="stat-card">
                <h3>$${montoTotalVentas.toFixed(2)}</h3>
                <p>Monto Total</p>
            </div>
            <div class="stat-card">
                <h3>$${promedioVenta.toFixed(2)}</h3>
                <p>Promedio por Venta</p>
            </div>
            <div class="stat-card">
                <h3>${totalClientes}</h3>
                <p>Total Clientes</p>
            </div>
            <div class="stat-card">
                <h3>${totalCafes}</h3>
                <p>Tipos de Café</p>
            </div>
            <div class="stat-card">
                <h3>${totalAuditorias}</h3>
                <p>Registros de Auditoría</p>
            </div>
        `;
        
        container.innerHTML = html;
    } catch (error) {
        console.error('Error al cargar estadísticas:', error);
        container.innerHTML = '<div class="alert alert-error">Error al cargar estadísticas</div>';
    }
}

// ============ UTILIDADES ============
function mostrarAlerta(containerId, mensaje, tipo) {
    const container = document.getElementById(containerId);
    const alertClass = tipo === 'success' ? 'alert-success' : 'alert-error';
    
    container.innerHTML = `<div class="alert ${alertClass}">${mensaje}</div>`;
    
    setTimeout(() => {
        container.innerHTML = '';
    }, 5000);
}

function formatDate(dateString) {
    if (!dateString) return 'N/A';
    const date = new Date(dateString);
    return date.toLocaleDateString('es-ES');
}

function formatDateTime(dateTimeString) {
    if (!dateTimeString) return 'N/A';
    const date = new Date(dateTimeString);
    return date.toLocaleString('es-ES');
}

function getBadgeClass(accion) {
    switch(accion) {
        case 'CREACION':
            return 'badge-creacion';
        case 'MODIFICACION':
            return 'badge-modificacion';
        case 'ELIMINACION':
            return 'badge-eliminacion';
        default:
            return '';
    }
}
