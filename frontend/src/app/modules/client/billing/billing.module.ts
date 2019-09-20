import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbDatepickerModule, NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';
import {FeeModule} from './fee/fee.module';
import {SharedModule} from '../../../shared/shared.module';
import {BillingRoutingModule} from './billing-routing.module';

@NgModule({
  declarations: [],

  imports: [
    CommonModule,
    ReactiveFormsModule,
    BillingRoutingModule,
    FeeModule,

    NgbDatepickerModule,
    NgbTooltipModule,
    SharedModule
  ],
  providers: []
})
export class BillingModule {
}
