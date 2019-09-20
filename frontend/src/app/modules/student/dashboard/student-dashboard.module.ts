import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  NgZorroAntdModule,
} from 'ng-zorro-antd';

import {StudentDashboardService} from '../core/service/student-dashboard.service';
import {StudentDashboardRoutingModule} from './student-dashboard-routing.module';
import {StudentDashboardComponent} from './student-dashboard.component';

@NgModule({
  imports: [
    CommonModule,
    StudentDashboardRoutingModule,
    NgZorroAntdModule
  ],
  declarations: [
    StudentDashboardComponent
  ],
  providers: [
    StudentDashboardService,
  ]
})
export class StudentDashboardModule {
}
