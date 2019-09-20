import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbDatepickerModule, NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';

import {SharedModule} from '../../../../shared/shared.module';
import {ExamResultService} from '../../core/service/exam/exam-result.service';
import {ExamResultRoutingModule} from './exam-result-routing.module';
import {ExamResultListComponent} from './exam-result-list.component';
import {ExamResultComponent} from './exam-result.component';
import {NgZorroAntdModule} from 'ng-zorro-antd';


@NgModule({
  declarations: [
    ExamResultListComponent,
    ExamResultComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ExamResultRoutingModule,
    SharedModule,
    NgbDatepickerModule,
    NgbTooltipModule,
    NgZorroAntdModule
  ],
  providers: [
    ExamResultService,
    DatePipe
  ]
})
export class ExamResultModule {
}
