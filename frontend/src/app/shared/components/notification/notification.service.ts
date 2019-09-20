import {EventEmitter, Injectable, Output} from '@angular/core';
import {NotificationModel} from './notificatoin.model';

@Injectable()
export class NotificationService {

  @Output() change: EventEmitter<NotificationModel> = new EventEmitter();
  model: NotificationModel;

  success(message: string): void {
    console.log('Before succes');
    this.model = new NotificationModel(null, message, 'success');
    this.change.emit(this.model);
    console.log('After succes');
  }

  failed(message: string): void {
    this.change.emit(new NotificationModel(null, message, 'failed'));
  }

  notify(title: string, message: string): void {
    this.change.emit(new NotificationModel(title, message, 'notify'));
  }
}
