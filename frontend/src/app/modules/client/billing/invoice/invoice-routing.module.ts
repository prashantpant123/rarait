import {NgModule} from '@angular/core';
import {
  RouterModule,
  Routes
} from '@angular/router';
import {InvoiceComponent} from './invoice.component';
import {InvoiceListComponent} from './invoice-list.component';
import {InvoiceGenerateComponent} from './invoice-generate.component';

const routes: Routes = [
  {
    path: ':invoice_id/detail',
    component: InvoiceComponent
  },
  {
    path: '',
    component: InvoiceListComponent
  },
  {
    path: 'create',
    component: InvoiceGenerateComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InvoiceRoutingModule {

}
