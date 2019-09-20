import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NgbDropdownModule} from '@ng-bootstrap/ng-bootstrap';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AdminRoutingModule} from './admin-routing.module';
import {AdminHeaderComponent} from './navigation/admin-header.component';
import {AdminSidebarComponent} from './navigation/admin-sidebar.component';
import {AdminLayoutComponent} from './admin-layout.component';

import {AdminSchoolModule} from './school/admin-school.module';
import {AdminSettingModule} from './setting/admin-setting.module';
import {NgZorroAntdModule} from 'ng-zorro-antd';
import {SharedModule} from '../../shared/shared.module';

@NgModule({
  declarations: [
    AdminLayoutComponent,
    AdminHeaderComponent,
    AdminSidebarComponent
  ],

  imports: [
    CommonModule,
    NgbDropdownModule,
    FormsModule,
    ReactiveFormsModule,
    NgZorroAntdModule,

    SharedModule,
    AdminRoutingModule,
    AdminSchoolModule,
    AdminSettingModule
  ],

  providers: [
  ]
})
export class AdminModule {
}
