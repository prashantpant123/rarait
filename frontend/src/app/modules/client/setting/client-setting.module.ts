import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';

import {SharedModule} from '../../../shared/shared.module';
import {
  NgZorroAntdModule
} from 'ng-zorro-antd';
import {UserProfileComponent} from './profile/user-profile.component';
import {ClientSettingRoutingModule} from './client-setting-routing.module';

@NgModule({
  declarations: [
    UserProfileComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    NgbTooltipModule,
    NgZorroAntdModule,

    ClientSettingRoutingModule,
    SharedModule
  ],
  providers: [
    DatePipe,
  ]
})
export class ClientSettingModule {
}
