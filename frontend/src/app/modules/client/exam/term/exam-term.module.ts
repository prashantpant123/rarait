import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgZorroAntdModule} from 'ng-zorro-antd';

import {SharedModule} from '../../../../shared/shared.module';
import {ExamTermAddComponent} from './exam-term-add.component';
import {ExamTermService} from '../../core/service/exam/exam-term.service';
import {ExamTermRoutineModule} from './exam-term-routine.module';
import {ExamTermListComponent} from './exam-term-list.component';

@NgModule({
  declarations: [
    ExamTermAddComponent,
    ExamTermListComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ExamTermRoutineModule,
    SharedModule,

    NgZorroAntdModule,
  ],
  providers: [
    ExamTermService
  ]
})
export class ExamTermModule {
}
