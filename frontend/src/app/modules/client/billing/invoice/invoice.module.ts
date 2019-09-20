import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';

import {InvoiceRoutingModule} from './invoice-routing.module';
import {SharedModule} from '../../../../shared/shared.module';
import {InvoiceComponent} from './invoice.component';
import {InvoiceGenerateComponent} from './invoice-generate.component';
import {InvoiceListComponent} from './invoice-list.component';

@NgModule({
  declarations: [
    InvoiceComponent,
    InvoiceGenerateComponent,
    InvoiceListComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    InvoiceRoutingModule,
    SharedModule
  ],
  providers: []
})
export class InvoiceModule {

}
