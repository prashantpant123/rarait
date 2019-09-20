import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TranslateModule} from '@ngx-translate/core';
import {NgbDropdownModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {ClientHeaderComponent} from './navigation/client-header.component';
import {ClientSidebarComponent} from './navigation/client-sidebar.component';

import {ClientRoutingModule} from './client-routing.module';
import {ClientLayoutComponent} from './client-layout.component';
import {StudentModule} from './student/student.module';
import {ExamModule} from './exam/exam.module';
import {TransportModule} from './transport/transport.module';
import {AcademicSessionModule} from './academic-session/academic-session.module';
import {StaffModule} from './staff/staff.module';
import {SubjectModule} from './subject/subject.module';
import {ClassModule} from './class/class.module';
import {AuthRoleGuard} from '../../shared';
import {HeaderService} from './core/service/header.service';
import {AttendanceModule} from './attendance/attendance.module';
import {NgZorroAntdModule} from 'ng-zorro-antd';
import {BillingModule} from "./billing/billing.module";

@NgModule({
  declarations: [
    ClientLayoutComponent,
    ClientHeaderComponent,
    ClientSidebarComponent
  ],
  imports: [
    CommonModule,
    TranslateModule,
    NgbDropdownModule,
    FormsModule,
    ReactiveFormsModule,
    NgZorroAntdModule,

    ClientRoutingModule,
    StudentModule,
    ExamModule,
    TransportModule,
    AcademicSessionModule,
    StaffModule,
    SubjectModule,
    BillingModule,
    ClassModule,
    AttendanceModule
  ],

  providers: [
    HeaderService
  ]
})
export class ClientModule {
}
