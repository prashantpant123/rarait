import {NgModule} from '@angular/core';
import {
  CommonModule,
  DatePipe
} from '@angular/common';
import {
  FormsModule,
  ReactiveFormsModule
} from '@angular/forms';
import {
  NgbDatepickerModule,
  NgbTooltipModule
} from '@ng-bootstrap/ng-bootstrap';
import {NgZorroAntdModule} from 'ng-zorro-antd';

import {AcademicSessionAddComponent} from './academic-session-add.component';
import {AcademicSessionService} from '../core/service/academic-session.service';
import {AcademicSessionListComponent} from './academic-session-list.component';
import {AcademicSessionRoutingModule} from './academic-session-routing.module';
import {SharedModule} from '../../../shared/shared.module';
import {AcademicSessionComponent} from './academic-session.component';

@NgModule({
  declarations: [
    AcademicSessionAddComponent,
    AcademicSessionListComponent,
    AcademicSessionComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgbDatepickerModule,
    NgbTooltipModule,
    NgZorroAntdModule,

    AcademicSessionRoutingModule,
    SharedModule
  ],
  providers: [
    DatePipe,
    AcademicSessionService
  ]
})
export class AcademicSessionModule {
}
