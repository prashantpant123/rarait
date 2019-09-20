import {NgModule} from '@angular/core';
import {CommonModule, DatePipe, TitleCasePipe} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';
import {
  NgZorroAntdModule
} from 'ng-zorro-antd';

import {StaffAddComponent} from './staff-add.component';
import {StaffListComponent} from './staff-list.component';
import {StaffRoutingModule} from './staff-routing.module';
import {StaffService} from '../core/service/staff.service';
import {SharedModule} from '../../../shared/shared.module';
import {StaffComponent} from './staff.component';
import {InstituteFileUploadService} from '../core/service/institute-file-upload.service';
import {NationalityService} from '../../../shared/services/nationality.service';
import {BloodGroupService} from '../../../shared/services/blood-group.service';
import {MaritalStatusService} from '../../../shared/services/marital-status.service';

@NgModule({
  declarations: [
    StaffAddComponent,
    StaffListComponent,
    StaffComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    NgbTooltipModule,
    NgZorroAntdModule,
    StaffRoutingModule,
    SharedModule
  ],
  providers: [
    DatePipe,
    TitleCasePipe,
    StaffService,
    InstituteFileUploadService,
    BloodGroupService,
    NationalityService,
    MaritalStatusService
  ]
})
export class StaffModule {

}
