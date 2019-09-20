import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {SectionComponent} from './section.component';
import {SectionAddComponent} from './section-add.component';
import {SectionListComponent} from './section-list.component';


const routes: Routes = [
  {
    path: '',
    component: SectionListComponent
  },
  {
    path: 'create',
    component: SectionAddComponent
  },
  {
    path: ':section_id/detail',
    component: SectionComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SectionRoutingModule {
}
