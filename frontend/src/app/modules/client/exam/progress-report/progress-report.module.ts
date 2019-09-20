import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';

import {NgZorroAntdModule} from 'ng-zorro-antd';
import {ProgressReportListComponent} from './progress-report-list.component';
import {SharedModule} from '../../../../shared/shared.module';
import {ExamService} from '../../core/service/exam/exam.service';
import {ProgressReportRoutingModule} from './progress-report-routing.module';

@NgModule({
  declarations: [
    ProgressReportListComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    NgbTooltipModule,
    NgZorroAntdModule,

    SharedModule,
    ProgressReportRoutingModule
  ],
  providers: [
    ExamService,
    DatePipe
  ]
})
export class ProgressReportModule {
}
