import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TranslateModule} from '@ngx-translate/core';

import {SignupComponent} from './signup/signup.component';
import {AccessDeniedComponent} from './access-denied/access-denied.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {ServerErrorComponent} from './server-error/server-error.component';

import {StaticRoutingModule} from './static-routing.module';
import {SharedModule} from '../../shared/shared.module';
import {StaticComponent} from './static.component';

@NgModule({
  declarations: [
    SignupComponent,
    AccessDeniedComponent,
    NotFoundComponent,
    ServerErrorComponent,
    StaticComponent],

  imports: [
    CommonModule,
    TranslateModule,
    StaticRoutingModule,
    SharedModule
  ],

})
export class StaticModule {
}
