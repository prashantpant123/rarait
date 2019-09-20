import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {ExamAddComponent} from './exam-add.component';
import {ExamListComponent} from './exam-list.component';
import {ExamComponent} from './exam.component';

const routes: Routes = [
  {
    path: '',
    component: ExamListComponent
  },
  {
    path: 'create',
    component: ExamAddComponent
  },
  {
    path: ':exam_id/detail',
    component: ExamComponent
  },

  {
    path: 'routine',
    loadChildren: './routine/exam-routine.module#ExamRoutineModule'
  },
  {
    path: 'result',
    loadChildren: './result/exam-result.module#ExamResultModule'
  },
  {
    path: 'progress-report',
    loadChildren: './progress-report/progress-report.module#ProgressReportModule'
  },
  {
    path: 'grade',
    loadChildren: './grade/grade.module#GradeModule'
  },
  {
    path: 'term',
    loadChildren: './term/exam-term.module#ExamTermModule'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExamRoutingModule {
}
