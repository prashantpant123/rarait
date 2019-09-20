import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {StudentAddComponent} from './student-add.component';
import {StudentListComponent} from './student-list.component';
import {StudentComponent} from './student.component';

const routes: Routes = [
  {
    path: '',
    component: StudentListComponent
  },
  {
    path: 'create',
    component: StudentAddComponent
  },
  {
    path: ':student_id/detail',
    component: StudentComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule {
}
