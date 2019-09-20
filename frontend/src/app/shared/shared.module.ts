import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  FormsModule,
  ReactiveFormsModule
} from '@angular/forms';

import {ModalService} from './components/modal/modal.service';
import {AuthRoleGuard} from './guard';
import {ComponentModule} from './components';
import {ConfirmationDialogSevice} from './components/confirmation-dailog/confirmation-dialog.sevice';
import {AuthenticationService} from './services/authentication.service';
import {ReadOnlyService} from './components/read-only/read-only.service';

@NgModule({
  declarations: [],

  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ComponentModule,
  ],

  exports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    ComponentModule
  ],
  providers: [
    AuthRoleGuard,
    ModalService,
    ConfirmationDialogSevice,
    ReadOnlyService,
    AuthenticationService
  ]
})
export class SharedModule {
}
