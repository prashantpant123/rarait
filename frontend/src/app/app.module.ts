import {CommonModule, HashLocationStrategy, LocationStrategy} from '@angular/common';
import {
  HTTP_INTERCEPTORS,
  HttpClient,
  HttpClientModule
} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {registerLocaleData} from '@angular/common';
import en from '@angular/common/locales/en';

import {AppRoutingModule} from './app-routing.module';

import {StaticModule} from './modules/static/static.module';
import {AuthModule} from './modules/auth/auth.module';
import {AdminModule} from './modules/admin/admin.module';
import {ClientModule} from './modules/client/client.module';
import {SharedModule} from './shared/shared.module';

import {AppComponent} from './app.component';
import {AuthRoleGuard} from './shared';
import {HttpConfigInterceptor, JwtInterceptor} from './helpers';

registerLocaleData(en);

import {StudentModule} from './modules/student';
import {en_US, NZ_I18N, NZ_MESSAGE_CONFIG} from 'ng-zorro-antd';

// AoT requires an exported function for factories
export const createTranslateLoader = (http: HttpClient) => {
  /* for development
  return new TranslateHttpLoader(
      http,
      '/start-angular/SB-Admin-BS4-Angular-6/master/dist/assets/i18n/',
      '.json'
  ); */
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
};

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: createTranslateLoader,
        deps: [HttpClient]
      }
    }),
    AppRoutingModule,
    AuthModule,
    StaticModule,
    AdminModule,
    ClientModule,
    StudentModule,
    SharedModule
  ],
  declarations: [
    AppComponent
  ],
  providers: [
    {provide: AuthRoleGuard},
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: HttpConfigInterceptor, multi: true},
    {provide: LocationStrategy, useClass: HashLocationStrategy},
    {provide: NZ_I18N, useValue: en_US},
    {provide: NZ_MESSAGE_CONFIG, useValue: {nzDuration: 7000, nzPauseOnHover: true, nzMaxStack: 1, nzAnimate: true, nzTop: 70}}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
