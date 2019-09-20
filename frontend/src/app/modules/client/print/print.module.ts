import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {SharedModule} from '../../../shared/shared.module';
import {HeaderService} from '../core/service/header.service';
import {StudentProfileTemplateComponent} from "./student-profile-template.component";
import {PrintLayoutComponent} from "./print-layout.component";
import {PrintRoutingModule} from "./print-routing.module";


@NgModule({
  declarations: [
  StudentProfileTemplateComponent,
    PrintLayoutComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    PrintRoutingModule
  ],
  providers: [

  ]
})
export class PrintModule {
}
