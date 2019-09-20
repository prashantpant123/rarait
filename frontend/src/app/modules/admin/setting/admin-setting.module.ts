import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {AdminSettingComponent} from './admin-setting.component';
import {AdminSettingRoutingModule} from './admin-setting-routing.module';
import {AdminUserService} from '../core/service/admin-user.service';
import {ComponentModule} from '../../../shared';
import {SharedModule} from '../../../shared/shared.module';
import {AdminUserComponent} from './user/admin-user.component';
import {AdminUserListComponent} from './user/admin-user-list.component';
import {AdminUserProfileComponent} from './user/admin-user-profile.component';
import {NgZorroAntdModule} from 'ng-zorro-antd';

@NgModule({
  declarations: [
    AdminSettingComponent,
    AdminUserComponent,
    AdminUserListComponent,
    AdminUserProfileComponent
  ],

  imports: [
    CommonModule,
    NgbModule,
    AdminSettingRoutingModule,
    ComponentModule,
    SharedModule,
    NgZorroAntdModule
  ],

  providers: [
    DatePipe,
    AdminUserService
  ]
})
export class AdminSettingModule {
}
