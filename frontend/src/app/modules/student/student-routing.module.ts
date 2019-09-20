import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {StudentLayoutComponent} from './student-layout.component';

const routes: Routes = [
  {
    path: '',
    component: StudentLayoutComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule {
}
