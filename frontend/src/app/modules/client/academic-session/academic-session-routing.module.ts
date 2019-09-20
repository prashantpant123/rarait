import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {AcademicSessionListComponent} from './academic-session-list.component';
import {AcademicSessionAddComponent} from './academic-session-add.component';
import {AcademicSessionComponent} from './academic-session.component';

const routes: Routes = [
  {
    path: '',
    component: AcademicSessionListComponent
  },
  {
    path: 'create',
    component: AcademicSessionAddComponent
  },
  {
    path: ':session_id/detail',
    component: AcademicSessionComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AcademicSessionRoutingModule {
}
