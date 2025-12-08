import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import SharedModule from 'app/shared/shared.module';

interface ProductoVenta {
  id: number;
  nombre: string;
  cantidad: number;
  precioUnitario: number;
}

@Component({
  selector: 'app-gestion-ventas',
  templateUrl: './gestion-ventas.component.html',
  styleUrl: './gestion-ventas.component.scss',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, SharedModule],
})
export default class GestionVentasComponent implements OnInit {
  ventaForm!: FormGroup;
  productosSeleccionados: ProductoVenta[] = [];
  totalVenta: number = 0;

  // Lista de productos disponibles
  productosDisponibles = [
    { id: 1, nombre: 'Café Espresso', precioUnitario: 25.00 },
    { id: 2, nombre: 'Café Americano', precioUnitario: 20.00 },
    { id: 3, nombre: 'Café Latte', precioUnitario: 30.00 },
    { id: 4, nombre: 'Cappuccino', precioUnitario: 32.00 },
    { id: 5, nombre: 'Mocca', precioUnitario: 35.00 },
    { id: 6, nombre: 'Café Cortado', precioUnitario: 22.00 },
  ];

  private fb = inject(FormBuilder);

  ngOnInit(): void {
    this.inicializarFormulario();
  }

  inicializarFormulario(): void {
    this.ventaForm = this.fb.group({
      cliente: ['', [Validators.required, Validators.minLength(3)]],
      producto: ['', Validators.required],
      cantidad: [1, [Validators.required, Validators.min(1), Validators.max(100)]],
      notas: [''],
    });
  }

  agregarProducto(): void {
    if (this.ventaForm.invalid) {
      return;
    }

    const productoSeleccionado = this.productosDisponibles.find(
      p => p.id === parseInt(this.ventaForm.get('producto')?.value)
    );

    if (productoSeleccionado) {
      const cantidad = this.ventaForm.get('cantidad')?.value;
      const productoExistente = this.productosSeleccionados.find(
        p => p.id === productoSeleccionado.id
      );

      if (productoExistente) {
        productoExistente.cantidad += cantidad;
      } else {
        this.productosSeleccionados.push({
          id: productoSeleccionado.id,
          nombre: productoSeleccionado.nombre,
          cantidad: cantidad,
          precioUnitario: productoSeleccionado.precioUnitario,
        });
      }

      this.calcularTotal();
      this.ventaForm.patchValue({
        producto: '',
        cantidad: 1,
      });
    }
  }

  eliminarProducto(index: number): void {
    this.productosSeleccionados.splice(index, 1);
    this.calcularTotal();
  }

  calcularTotal(): void {
    this.totalVenta = this.productosSeleccionados.reduce(
      (total, producto) => total + producto.cantidad * producto.precioUnitario,
      0
    );
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
      fecha: new Date().toISOString(),
    };

    console.log('Venta registrada:', venta);
    alert(`Venta registrada exitosamente para ${venta.cliente}. Total: $${venta.total.toFixed(2)}`);

    // Reiniciar el formulario
    this.productosSeleccionados = [];
    this.totalVenta = 0;
    this.ventaForm.reset({
      cantidad: 1,
    });
  }

  limpiarFormulario(): void {
    this.productosSeleccionados = [];
    this.totalVenta = 0;
    this.ventaForm.reset({
      cantidad: 1,
    });
  }
}
