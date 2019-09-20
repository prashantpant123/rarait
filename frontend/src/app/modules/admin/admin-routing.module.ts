import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AdminLayoutComponent} from './admin-layout.component';

const routes: Routes = [
  {
    path: '',
    component: AdminLayoutComponent,
    children: [
      {
        path: '', redirectTo: 'dashboard', pathMatch: 'full'
      },
      {
        path: 'dashboard',
        loadChildren: './dashboard/admin-dashboard.module#AdminDashboardModule'
      },
      {
        path: 'school',
        loadChildren: './school/admin-school.module#AdminSchoolModule'
      },
      {
        path: 'setting',
        loadChildren: './setting/admin-setting.module#AdminSettingModule'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {
}
