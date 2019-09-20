import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DashboardComponent} from './dashboard.component';
import {DashboardRoutingModule} from './dashboard-routing.module';

import {DashboardService} from '../core/service/dashboard.service';
import {NgZorroAntdModule} from 'ng-zorro-antd';

@NgModule({
  imports: [
    CommonModule,
    DashboardRoutingModule,
    NgZorroAntdModule
  ],
  declarations: [
    DashboardComponent
  ],
  providers: [
    DashboardService,
  ]
})
export class DashboardModule {
}
