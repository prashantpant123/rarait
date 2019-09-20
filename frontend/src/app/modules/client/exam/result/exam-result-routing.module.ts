import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {ExamResultListComponent} from './exam-result-list.component';

const routes: Routes = [
  {
    path: '',
    component: ExamResultListComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExamResultRoutingModule {
}
