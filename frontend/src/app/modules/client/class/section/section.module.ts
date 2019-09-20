import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';

import {
  NgZorroAntdModule
} from 'ng-zorro-antd';
import {SectionComponent} from './section.component';
import {SectionService} from '../../core/service/section.service';
import {SectionAddComponent} from './section-add.component';
import {ClassService} from '../../core/service/class.service';
import {SectionListComponent} from './section-list.component';
import {SectionRoutingModule} from './section-routing.module';
import {SharedModule} from '../../../../shared/shared.module';

@NgModule({
  declarations: [
    SectionComponent,
    SectionAddComponent,
    SectionListComponent
  ],

  imports: [
    CommonModule,
    ReactiveFormsModule,
    SectionRoutingModule,

    NgbTooltipModule,
    NgZorroAntdModule,

    SharedModule
  ],

  providers: [
    SectionService,
    ClassService,
  ]
})
export class SectionModule {
}
