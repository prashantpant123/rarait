import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgZorroAntdModule} from 'ng-zorro-antd';
import {FileUploadModule} from 'ng2-file-upload';

import {AdminSchoolListComponent} from './admin-school-list.component';
import {AdminSchoolAddComponent} from './admin-school-add.component';

import {AdminSchoolRoutingModule} from './admin-school-routing.module';
import {AdminSchoolService} from '../core/service/admin-school.service';
import {AddressService} from '../../../shared/services/address.service';
import {SharedModule} from '../../../shared/shared.module';
import {AdminSchoolComponent} from './admin-school.component';
import {AdminFileUploadService} from '../core/service/admin-file-upload.service';

@NgModule({
  declarations: [
    AdminSchoolListComponent,
    AdminSchoolAddComponent,
    AdminSchoolComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    NgZorroAntdModule,
    AdminSchoolRoutingModule,
    FileUploadModule
  ],

  providers: [
    AdminSchoolService,
    AdminFileUploadService,
    AddressService
  ]
})
export class AdminSchoolModule {
}
