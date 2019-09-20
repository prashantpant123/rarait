import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TranslateModule} from '@ngx-translate/core';
import {HTTP_INTERCEPTORS} from '@angular/common/http';

import {SharedModule} from '../../shared/shared.module';
import {ForgotLoginComponent} from './forgot-login/forgot-login.component';
import {ResetLoginComponent} from './reset-login/reset-login.component';
import {LoginComponent} from './login/login.component';
import {AuthRoutingModule} from './auth-routing.module';

@NgModule({
  declarations: [
    ForgotLoginComponent,
    ResetLoginComponent,
    LoginComponent
  ],

  imports: [
    CommonModule,
    TranslateModule,
    AuthRoutingModule,
    SharedModule
  ],
  providers: []
})
export class AuthModule {
  constructor() {
  }
}
