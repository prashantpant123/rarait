import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AdminSettingComponent} from './admin-setting.component';
import {AdminUserProfileComponent} from './user/admin-user-profile.component';

const routes: Routes = [
  {
    path: '',
    component: AdminSettingComponent
  }, {
    path: 'profile/:user_id',
    component: AdminUserProfileComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminSettingRoutingModule {
}
