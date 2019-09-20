import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {StaffListComponent} from './staff-list.component';
import {StaffAddComponent} from './staff-add.component';
import {StaffComponent} from './staff.component';

const routes: Routes = [

  {
    path: '',
    component: StaffListComponent
  },
  {
    path: 'create',
    component: StaffAddComponent
  },
  {
    path: ':staff_id/detail',
    component: StaffComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StaffRoutingModule {


}
