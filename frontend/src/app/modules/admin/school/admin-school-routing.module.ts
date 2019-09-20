import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AdminSchoolListComponent} from './admin-school-list.component';
import {AdminSchoolAddComponent} from './admin-school-add.component';
import {AdminSchoolComponent} from './admin-school.component';

const routes: Routes = [
  {
    path: '',
    component: AdminSchoolListComponent
  },
  {
    path: 'create',
    component: AdminSchoolAddComponent
  },
  {
    path: ':institute_id/detail',
    component: AdminSchoolComponent
  },
  {
    path: ':institute_id/edit',
    component: AdminSchoolAddComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminSchoolRoutingModule {
}
