import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';

import {StudentAddComponent} from './student-add.component';
import {StudentListComponent} from './student-list.component';
import {StudentRoutingModule} from './student-routing.module';
import {StudentService} from '../core/service/student.service';
import {SharedModule} from '../../../shared/shared.module';
import {OccupationService} from '../../../shared/services/occupation.service';
import {ClassService} from '../core/service/class.service';
import {AddressService} from '../../../shared/services/address.service';
import {StudentComponent} from './student.component';
import {

  NgZorroAntdModule
} from 'ng-zorro-antd';
import {InstituteFileUploadService} from '../core/service/institute-file-upload.service';
import {BloodGroupService} from '../../../shared/services/blood-group.service';
import {NationalityService} from '../../../shared/services/nationality.service';
import {HeaderService} from '../core/service/header.service';
import {PrintService} from "../print/print_service";

@NgModule({
  declarations: [
    StudentAddComponent,
    StudentListComponent,
    StudentComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    NgbTooltipModule,
    NgZorroAntdModule,

    StudentRoutingModule,
    SharedModule
  ],
  providers: [
    DatePipe,
    OccupationService,
    ClassService,
    AddressService,
    StudentService,
    InstituteFileUploadService,
    BloodGroupService,
    NationalityService,
    HeaderService,
  ]
})
export class StudentModule {
}
