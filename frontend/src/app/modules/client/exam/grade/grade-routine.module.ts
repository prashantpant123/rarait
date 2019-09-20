import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {GradeListComponent} from './grade-list.component';
import {GradeAddComponent} from './grade-add.component';
import {GradeComponent} from './grade.component';


const routes: Routes = [
  {
    path: '',
    component: GradeListComponent
  },
  {
    path: 'create',
    component: GradeAddComponent
  },
  {
    path: ':grade_id/detail',
    component: GradeComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GradeRoutineModule {
}
