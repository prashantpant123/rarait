import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {TranslateModule} from '@ngx-translate/core';

import {StudentLayoutComponent} from './student-layout.component';
import {StudentRoutingModule} from './student-routing.module';
import {StudentHeaderComponent} from './navigation/student-header.component';
import {StudentSidebarComponent} from './navigation/student-sidebar.component';

@NgModule({
  declarations: [
    StudentLayoutComponent,
    StudentHeaderComponent,
    StudentSidebarComponent
  ],
  imports: [
    CommonModule,
    TranslateModule,
    StudentRoutingModule,
    RouterModule
  ],
  providers: [
  ]
})
export class StudentModule {

}
