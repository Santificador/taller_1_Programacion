# 🔌 Guía de Integración con Backend

## 📡 Próximos Pasos para Integración

Este documento describe cómo integrar el componente de **Gestión de Ventas** con el backend de la aplicación.

---

## 1. Crear Servicio de Ventas

### Ubicación:
```
src/main/webapp/app/entities/gestion-ventas/
└── gestion-ventas.service.ts
```

### Contenido:
```typescript
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ProductoDTO {
  id: number;
  nombre: string;
  precioUnitario: number;
  stock: number;
}

export interface VentaDTO {
  id?: number;
  cliente: string;
  productos: ProductoVentaDTO[];
  total: number;
  notas?: string;
  fecha?: string;
}

export interface ProductoVentaDTO {
  id: number;
  nombre: string;
  cantidad: number;
  precioUnitario: number;
}

@Injectable({
  providedIn: 'root',
})
export class GestionVentasService {
  private http = inject(HttpClient);
  private apiUrl = '/api/ventas';
  private productosUrl = '/api/productos';

  /**
   * Obtiene la lista de productos disponibles
   */
  getProductos(): Observable<ProductoDTO[]> {
    return this.http.get<ProductoDTO[]>(this.productosUrl);
  }

  /**
   * Registra una nueva venta
   */
  registrarVenta(venta: VentaDTO): Observable<VentaDTO> {
    return this.http.post<VentaDTO>(this.apiUrl, venta);
  }

  /**
   * Obtiene el historial de ventas
   */
  obtenerVentas(page: number = 0, pageSize: number = 20): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=${page}&size=${pageSize}`);
  }

  /**
   * Obtiene detalles de una venta
   */
  obtenerVenta(id: number): Observable<VentaDTO> {
    return this.http.get<VentaDTO>(`${this.apiUrl}/${id}`);
  }

  /**
   * Actualiza una venta existente
   */
  actualizarVenta(id: number, venta: VentaDTO): Observable<VentaDTO> {
    return this.http.put<VentaDTO>(`${this.apiUrl}/${id}`, venta);
  }

  /**
   * Elimina una venta
   */
  eliminarVenta(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
```

---

## 2. Actualizar Componente para Usar el Servicio

### Modificar el TypeScript:
```typescript
import { Component, OnInit, inject } from '@angular/core';
import { GestionVentasService, ProductoDTO } from './gestion-ventas.service';

@Component({
  // ... config
})
export default class GestionVentasComponent implements OnInit {
  private ventasService = inject(GestionVentasService);
  productosDisponibles: ProductoDTO[] = [];
  loading = false;

  ngOnInit(): void {
    this.inicializarFormulario();
    this.cargarProductos();
  }

  cargarProductos(): void {
    this.loading = true;
    this.ventasService.getProductos().subscribe({
      next: (productos) => {
        this.productosDisponibles = productos;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error cargando productos:', error);
        this.loading = false;
      },
    });
  }

  registrarVenta(): void {
    if (this.ventaForm.invalid || this.productosSeleccionados.length === 0) {
      alert('Por favor, complete el formulario y agregue al menos un producto');
      return;
    }

    const venta = {
      cliente: this.ventaForm.get('cliente')?.value,
      productos: this.productosSeleccionados,
      total: this.totalVenta,
      notas: this.ventaForm.get('notas')?.value,
    };

    this.loading = true;
    this.ventasService.registrarVenta(venta).subscribe({
      next: (ventaRegistrada) => {
        alert(`Venta registrada exitosamente. ID: ${ventaRegistrada.id}`);
        this.productosSeleccionados = [];
        this.totalVenta = 0;
        this.ventaForm.reset({ cantidad: 1 });
        this.loading = false;
      },
      error: (error) => {
        console.error('Error registrando venta:', error);
        alert('Error al registrar la venta. Intente nuevamente.');
        this.loading = false;
      },
    });
  }
}
```

---

## 3. Backend API Endpoints Requeridos

### GET /api/productos
**Descripción**: Obtiene lista de productos disponibles

**Response**:
```json
[
  {
    "id": 1,
    "nombre": "Café Espresso",
    "precioUnitario": 25.00,
    "stock": 100
  },
  {
    "id": 2,
    "nombre": "Café Americano",
    "precioUnitario": 20.00,
    "stock": 150
  }
]
```

---

### POST /api/ventas
**Descripción**: Crea una nueva venta

**Request Body**:
```json
{
  "cliente": "Carlos López",
  "productos": [
    {
      "id": 1,
      "nombre": "Café Espresso",
      "cantidad": 2,
      "precioUnitario": 25.00
    }
  ],
  "total": 50.00,
  "notas": "Entregar a domicilio"
}
```

**Response** (201 Created):
```json
{
  "id": 1,
  "cliente": "Carlos López",
  "productos": [...],
  "total": 50.00,
  "notas": "Entregar a domicilio",
  "fecha": "2025-12-08T14:30:00Z"
}
```

---

### GET /api/ventas
**Descripción**: Obtiene historial de ventas

**Query Parameters**:
- `page`: número de página (default: 0)
- `size`: cantidad por página (default: 20)

**Response**:
```json
{
  "content": [
    {
      "id": 1,
      "cliente": "Carlos López",
      "total": 50.00,
      "fecha": "2025-12-08T14:30:00Z"
    }
  ],
  "totalElements": 150,
  "totalPages": 8,
  "currentPage": 0
}
```

---

### GET /api/ventas/{id}
**Descripción**: Obtiene detalles de una venta específica

**Response**:
```json
{
  "id": 1,
  "cliente": "Carlos López",
  "productos": [...],
  "total": 50.00,
  "notas": "Entregar a domicilio",
  "fecha": "2025-12-08T14:30:00Z"
}
```

---

### PUT /api/ventas/{id}
**Descripción**: Actualiza una venta existente

**Request Body**: (mismo que POST)

---

### DELETE /api/ventas/{id}
**Descripción**: Elimina una venta

**Response**: 204 No Content

---

## 4. Manejo de Errores

### Crear Interceptor de Errores (Opcional)

```typescript
// error.interceptor.ts
import { Injectable, inject } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('HTTP Error:', error);

        let errorMessage = 'Error en la solicitud';

        if (error.error instanceof ErrorEvent) {
          errorMessage = `Error: ${error.error.message}`;
        } else {
          errorMessage = `Error ${error.status}: ${error.statusText}`;
          if (error.error?.message) {
            errorMessage = error.error.message;
          }
        }

        return throwError(() => new Error(errorMessage));
      })
    );
  }
}
```

---

## 5. Validaciones del Backend

### Ejemplo en Spring Boot:

```java
@PostMapping
public ResponseEntity<VentaDTO> crearVenta(@Valid @RequestBody VentaDTO venta) {
    // Validar cliente
    if (venta.getCliente() == null || venta.getCliente().length() < 3) {
        return ResponseEntity.badRequest().build();
    }

    // Validar productos
    if (venta.getProductos() == null || venta.getProductos().isEmpty()) {
        return ResponseEntity.badRequest().build();
    }

    // Validar stock
    for (ProductoVentaDTO producto : venta.getProductos()) {
        Producto p = productoRepository.findById(producto.getId())
            .orElseThrow();
        if (p.getStock() < producto.getCantidad()) {
            return ResponseEntity.badRequest().build();
        }
        // Decrementar stock
        p.setStock(p.getStock() - producto.getCantidad());
        productoRepository.save(p);
    }

    // Guardar venta
    Venta ventaGuardada = ventaRepository.save(venta);
    return ResponseEntity.status(201).body(ventaGuardada);
}
```

---

## 6. Agregar Loading Spinner

### Template HTML (gestion-ventas.component.html):
```html
@if (loading) {
  <div class="d-flex justify-content-center align-items-center" style="min-height: 400px">
    <div class="spinner-border text-primary" role="status">
      <span class="visually-hidden">Cargando...</span>
    </div>
  </div>
} @else {
  <!-- Contenido actual del componente -->
}
```

---

## 7. Confirmación de Acciones Críticas

### Diálogo de Confirmación (Opcional):
```typescript
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

deleteVenta(id: number): void {
  this.modalService.open(confirmModal).result.then(
    () => {
      this.ventasService.eliminarVenta(id).subscribe({
        next: () => {
          this.cargarVentas();
          alert('Venta eliminada exitosamente');
        },
        error: (error) => {
          alert('Error al eliminar la venta');
        },
      });
    },
    () => {
      // Usuario canceló
    }
  );
}
```

---

## 8. Testing

### Unit Test ejemplo:

```typescript
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { GestionVentasService } from './gestion-ventas.service';

describe('GestionVentasService', () => {
  let service: GestionVentasService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [GestionVentasService],
    });
    service = TestBed.inject(GestionVentasService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should fetch productos', () => {
    const mockProductos = [
      { id: 1, nombre: 'Espresso', precioUnitario: 25, stock: 100 },
    ];

    service.getProductos().subscribe((productos) => {
      expect(productos.length).toBe(1);
      expect(productos[0].nombre).toBe('Espresso');
    });

    const req = httpMock.expectOne('/api/productos');
    expect(req.request.method).toBe('GET');
    req.flush(mockProductos);
  });

  it('should create venta', () => {
    const mockVenta = {
      cliente: 'Test',
      productos: [],
      total: 0,
    };

    service.registrarVenta(mockVenta).subscribe((result) => {
      expect(result.id).toBeDefined();
    });

    const req = httpMock.expectOne('/api/ventas');
    expect(req.request.method).toBe('POST');
    req.flush({ ...mockVenta, id: 1 });
  });
});
```

---

## 9. Checklist de Integración

```
□ Crear servicio GestionVentasService
□ Inyectar servicio en componente
□ Implementar carga de productos desde API
□ Implementar registro de ventas
□ Crear endpoints en backend
□ Agregar validaciones en backend
□ Configurar interceptores de error
□ Agregar loading spinner
□ Implementar manejo de errores
□ Crear tests unitarios
□ Realizar pruebas E2E
□ Documentar endpoints
□ Configurar CORS si es necesario
□ Implementar autenticación si es necesaria
□ Agregar auditoría de cambios
```

---

## 10. Recursos Útiles

- [HttpClientModule - Angular Docs](https://angular.io/guide/http)
- [RxJS Operators](https://rxjs.dev/api)
- [Testing HTTP - Angular Docs](https://angular.io/guide/http#testing-http-requests)
- [Spring Boot REST API](https://spring.io/guides/gs/rest-service/)

---

**Documentación de Integración Completada** ✅
