import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';

import {SharedModule} from '../../../shared/shared.module';
import {AttendanceRoutingModule} from './attendance-routing.module';
import {AttendanceService} from '../core/service/attendance.service';
import {ClassService} from '../core/service/class.service';
import {AttendanceListComponent} from './attendance-list.component';
import {
  NgZorroAntdModule
} from 'ng-zorro-antd';

@NgModule({
  declarations: [
    AttendanceListComponent
  ],

  imports: [
    CommonModule,
    ReactiveFormsModule,
    AttendanceRoutingModule,
    NgbTooltipModule,
    NgZorroAntdModule,

    SharedModule
  ],

  providers: [
    AttendanceService,
    ClassService
  ]
})
export class AttendanceModule {
}
