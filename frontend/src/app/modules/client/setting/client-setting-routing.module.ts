import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {UserProfileComponent} from './profile/user-profile.component';

const routes: Routes = [
  {
    path: 'profile',
    component: UserProfileComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientSettingRoutingModule {
}
