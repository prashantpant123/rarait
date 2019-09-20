import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';

import {SubjectListComponent} from './subject-list.component';
import {SubjectAddComponent} from './subject-add.component';
import {SubjectRoutingModule} from './subject-routing.module';
import {SubjectService} from '../core/service/subject.service';
import {SharedModule} from '../../../shared/shared.module';
import {ClassService} from '../core/service/class.service';
import {SubjectComponent} from './subject.component';
import { NgZorroAntdModule} from 'ng-zorro-antd';

@NgModule({
  declarations: [
    SubjectListComponent,
    SubjectAddComponent,
    SubjectComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SubjectRoutingModule,
    SharedModule,
    NgbTooltipModule,
    NgZorroAntdModule,

  ],
  providers: [
    DatePipe,
    SubjectService,
    ClassService
  ]
})
export class SubjectModule {
}
