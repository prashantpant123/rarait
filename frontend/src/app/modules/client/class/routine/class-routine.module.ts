import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';

import {
  NgZorroAntdModule
} from 'ng-zorro-antd';
import {SectionService} from '../../core/service/section.service';
import {ClassService} from '../../core/service/class.service';
import {SharedModule} from '../../../../shared/shared.module';
import {ClassRoutineRoutingModule} from './class-routine-routing.module';
import {ClassRoutineListComponent} from './class-routine-list.component';

@NgModule({
  declarations: [
    ClassRoutineListComponent
  ],

  imports: [
    CommonModule,
    ReactiveFormsModule,
    ClassRoutineRoutingModule,

    NgbTooltipModule,
    NgZorroAntdModule,

    SharedModule
  ],

  providers: [
    SectionService,
    ClassService,
  ]
})
export class ClassRoutineModule {
}
