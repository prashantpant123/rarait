import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {ClassRoutineListComponent} from './class-routine-list.component';

const routes: Routes = [
  {
    path: '',
    component: ClassRoutineListComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClassRoutineRoutingModule {
}
