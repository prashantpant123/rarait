import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';

import {ExamListComponent} from './exam-list.component';
import {ExamAddComponent} from './exam-add.component';
import {ExamRoutingModule} from './exam-routing.module';
import {ExamService} from '../core/service/exam/exam.service';
import {SharedModule} from '../../../shared/shared.module';
import {ExamComponent} from './exam.component';
import {ExamResultModule} from './result/exam-result.module';
import {ExamRoutineModule} from './routine/exam-routine.module';
import {NgZorroAntdModule} from 'ng-zorro-antd';
import {ProgressReportModule} from './progress-report/progress-report.module';
import {GradeModule} from './grade/grade.module';
import {ExamTermModule} from './term/exam-term.module';

@NgModule({
  declarations: [
    ExamListComponent,
    ExamAddComponent,
    ExamComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    NgbTooltipModule,
    NgZorroAntdModule,

    ExamRoutingModule,
    SharedModule,
    ExamResultModule,
    ExamRoutineModule,
    ProgressReportModule,
    GradeModule,
    ExamTermModule
  ],
  providers: [
    ExamService,
    DatePipe
  ]
})
export class ExamModule {
}
