import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {FeeComponent} from './fee.component';
import {FeeListComponent} from './fee-list.component';
import {FeeAddComponent} from './fee-add.component';

const routes: Routes = [
  {
    path: '',
    component: FeeListComponent
  },
  {
    path: 'create',
    component: FeeAddComponent
  },
  {
    path: ':fee_id/detail',
    component: FeeComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FeeRoutingModule {
}
