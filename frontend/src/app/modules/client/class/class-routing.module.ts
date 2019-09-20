import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {ClassAddComponent} from './class-add.component';
import {ClassListComponent} from './class-list.component';
import {ClassComponent} from './class.component';

const routes: Routes = [
  {
    path: '',
    component: ClassListComponent
  },
  {
    path: 'create',
    component: ClassAddComponent
  },
  {
    path: ':class_id/detail',
    component: ClassComponent
  },
  {
    path: 'section',
    loadChildren: './section/section.module#SectionModule'
  },
  {
    path: 'routine',
    loadChildren: './routine/class-routine.module#ClassRoutineModule'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClassRoutingModule {
}
