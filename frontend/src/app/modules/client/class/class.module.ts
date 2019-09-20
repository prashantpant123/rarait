import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';

import {ClassRoutingModule} from './class-routing.module';
import {ClassAddComponent} from './class-add.component';
import {ClassListComponent} from './class-list.component';
import {ClassService} from '../core/service/class.service';
import {SharedModule} from '../../../shared/shared.module';
import {ClassComponent} from './class.component';
import {NgZorroAntdModule} from 'ng-zorro-antd';
import {SectionModule} from './section/section.module';
import {ClassRoutineModule} from './routine/class-routine.module';

@NgModule({
  declarations: [
    ClassAddComponent,
    ClassListComponent,
    ClassComponent
  ],

  imports: [
    CommonModule,
    ReactiveFormsModule,
    ClassRoutingModule,
    NgbTooltipModule,
    NgZorroAntdModule,
    SharedModule,
    SectionModule,
    ClassRoutineModule
  ],

  providers: [
    ClassService
  ]
})
export class ClassModule {
}
