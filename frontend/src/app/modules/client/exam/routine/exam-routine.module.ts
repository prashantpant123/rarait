import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';

import {SharedModule} from '../../../../shared/shared.module';
import {ExamRoutineRoutingModule} from './exam-routine-routing.module';
import {ExamRoutineService} from '../../core/service/exam/exam-routine.service';
import {ExamRoutineListComponent} from './exam-routine-list.component';
import {ExamRoutineAddComponent} from './exam-routine-add.component';
import {
  NgZorroAntdModule
} from 'ng-zorro-antd';

@NgModule({
  declarations: [
    ExamRoutineListComponent,
    ExamRoutineAddComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ExamRoutineRoutingModule,
    SharedModule,

    NgbTooltipModule,
    NgZorroAntdModule,
  ],
  providers: [
    ExamRoutineService,
    DatePipe
  ]
})
export class ExamRoutineModule {
}
