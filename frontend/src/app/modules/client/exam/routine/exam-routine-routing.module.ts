import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {ExamRoutineListComponent} from './exam-routine-list.component';
import {ExamRoutineAddComponent} from './exam-routine-add.component';

const routes: Routes = [
  {
    path: '',
    component: ExamRoutineListComponent
  },
  {
    path: 'create',
    component: ExamRoutineAddComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExamRoutineRoutingModule {
}
