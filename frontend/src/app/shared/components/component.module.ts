import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import {FileUploaderComponent} from './file-uploader/file-uploader.component';
import {ModalComponent} from './modal/modal.component';
import {ConfirmationDialogComponent} from './confirmation-dailog/confirmation-dialog.component';
import {ReadOnlyComponent} from './read-only/read-only.component';
import {BreadcrumbComponent} from './breadcrumb/breadcrumb.component';
import {ButtonNewCardComponent} from './button/button-new-card.component';
import {ButtonExcelCardComponent} from './button/button-excel-card.component';
import {DetailPageComponent} from './detail-page/detail-page.component';
import {StatBoxComponent} from './stats/stat-box.component';
import {
  NgZorroAntdModule,
  NzNotificationService
} from 'ng-zorro-antd';
import {ProfilePageComponent} from './profile-page/profile-page.component';
import {NotificationComponent} from './notification/notification.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    NgZorroAntdModule
  ],

  declarations: [
    FileUploaderComponent,
    ModalComponent,
    ConfirmationDialogComponent,
    ReadOnlyComponent,
    BreadcrumbComponent,
    ButtonNewCardComponent,
    ButtonExcelCardComponent,
    DetailPageComponent,
    StatBoxComponent,
    NotificationComponent,
    ProfilePageComponent
  ],

  exports: [
    ModalComponent,
    FileUploaderComponent,
    ConfirmationDialogComponent,
    ReadOnlyComponent,
    BreadcrumbComponent,
    ButtonNewCardComponent,
    ButtonExcelCardComponent,
    DetailPageComponent,
    StatBoxComponent,
    NotificationComponent,
    ProfilePageComponent
  ],

  providers: [
    NzNotificationService
  ],

  entryComponents: [
    ConfirmationDialogComponent
  ],
})
export class ComponentModule {
}
