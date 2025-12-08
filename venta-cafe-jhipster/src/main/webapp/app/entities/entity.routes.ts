import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'ventacafeappApp.adminAuthority.home.title' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'gestion-ventas',
    data: { pageTitle: 'ventacafeappApp.gestionVentas.home.title' },
    loadChildren: () => import('./gestion-ventas/gestion-ventas.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
