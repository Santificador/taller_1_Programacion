import { Routes } from '@angular/router';

import GestionVentasComponent from './gestion-ventas.component';

const gestionVentasRoute: Routes = [
  {
    path: '',
    component: GestionVentasComponent,
    data: { pageTitle: 'ventacafeappApp.gestionVentas.home.title' },
  },
];

export default gestionVentasRoute;
