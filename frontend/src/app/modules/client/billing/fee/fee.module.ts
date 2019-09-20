import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbDatepickerModule, NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';

import {SharedModule} from '../../../../shared/shared.module';
import {FeeService} from '../../core/service/billing/fee.service';
import {FeeComponent} from './fee.component';
import {FeeListComponent} from './fee-list.component';
import {FeeRoutingModule} from './fee-routing.module';
import {FeeAddComponent} from './fee-add.component';
import {NgZorroAntdModule} from 'ng-zorro-antd';

@NgModule({
  declarations: [
    FeeComponent,
    FeeListComponent,
    FeeAddComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    FeeRoutingModule,

    NgbDatepickerModule,
    NgbTooltipModule,
    NgZorroAntdModule
  ],
  providers: [
    FeeService,
    DatePipe
  ]
})
export class FeeModule {
}
