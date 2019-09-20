import {NgModule} from '@angular/core';
import {
  Routes,
  RouterModule
} from '@angular/router';

import {ExamTermAddComponent} from './exam-term-add.component';
import {ExamTermListComponent} from './exam-term-list.component';


const routes: Routes = [
  {
    path: '',
    component: ExamTermListComponent
  },
  {
    path: 'create',
    component: ExamTermAddComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExamTermRoutineModule {
}
