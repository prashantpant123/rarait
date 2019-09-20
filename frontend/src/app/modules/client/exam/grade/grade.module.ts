import {NgModule} from '@angular/core';
import {
  CommonModule,
  DatePipe
} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {
  NgZorroAntdModule
} from 'ng-zorro-antd';

import {SharedModule} from '../../../../shared/shared.module';
import {GradeRoutineModule} from './grade-routine.module';
import {GradeService} from '../../core/service/exam/grade.service';
import {GradeComponent} from './grade.component';
import {GradeAddComponent} from './grade-add.component';
import {GradeListComponent} from './grade-list.component';

@NgModule({
  declarations: [
    GradeComponent,
    GradeAddComponent,
    GradeListComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    GradeRoutineModule,
    SharedModule,

    NgZorroAntdModule,
  ],
  providers: [
    GradeService,
    DatePipe
  ]
})
export class GradeModule {
}
