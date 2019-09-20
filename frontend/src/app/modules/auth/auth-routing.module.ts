import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {ForgotLoginComponent} from '../auth/forgot-login/forgot-login.component';
import {ResetLoginComponent} from '../auth/reset-login/reset-login.component';
import {LoginComponent} from '../auth/login/login.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'forgot-password',
    component: ForgotLoginComponent
  },
  {
    path: 'reset-password',
    component: ResetLoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule {
}
