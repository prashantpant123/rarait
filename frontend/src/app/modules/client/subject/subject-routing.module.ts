import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {SubjectListComponent} from './subject-list.component';
import {SubjectAddComponent} from './subject-add.component';
import {SubjectComponent} from './subject.component';

const routes: Routes = [
  {
    path: '',
    component: SubjectListComponent
  },
  {
    path: 'create',
    component: SubjectAddComponent
  },
  {
    path: ':subject_id/detail',
    component: SubjectComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SubjectRoutingModule {
}
