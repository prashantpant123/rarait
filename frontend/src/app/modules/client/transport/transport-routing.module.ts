import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {TransportComponent} from './transport.component';
import {TransportListComponent} from './transport-list.component';
import {TransportAddComponent} from './transport-add.component';
import {TransportRouteListComponent, TransportRouteAddComponent, TransportRouteComponent} from './route';

const routes: Routes = [
  {
    path: '',
    component: TransportListComponent
  },
  {
    path: 'create',
    component: TransportAddComponent
  },
  {
    path: ':transport_id/detail',
    component: TransportComponent
  },
  {
    path: 'route',
    component: TransportRouteListComponent
  },
  {
    path: 'route/create',
    component: TransportRouteAddComponent
  },
  {
    path: 'route/:transport_route_id/detail',
    component: TransportRouteComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransportRoutingModule {
}
