import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {ProgressReportListComponent} from './progress-report-list.component';

const routes: Routes = [
  {
    path: '',
    component: ProgressReportListComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProgressReportRoutingModule {
}
