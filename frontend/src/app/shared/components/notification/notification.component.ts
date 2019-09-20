import {
  Component,
  Input,
  OnInit,
  ViewChild
} from '@angular/core';
import {NotificationService} from './notification.service';
import {NzNotificationService} from 'ng-zorro-antd';
import {NotificationModel} from './notificatoin.model';

@Component({
  selector: 'notification',
  templateUrl: 'notfication.component.html',
  styles: []
})
export class NotificationComponent implements OnInit {

  @Input() title: string;
  @Input() content: string;
  @ViewChild('notificationServer') templateServer;
  @ViewChild('notificationSuccess') templateSuccess;
  @ViewChild('notificationFailed') templateFailed;

  model: NotificationModel;

  constructor(private notificationService: NotificationService,
              private notification: NzNotificationService) {
  }

  ngOnInit(): void {
    this.serviceBind();
  }

  serviceBind() {
    this.notificationService.change.subscribe(data => {
      this.model = data;
      this.content = this.model.content;

      if (this.model.type === 'success') {
        this.showSuccess();
      } else if (this.model.type === 'failed') {
        this.showFailed();
      } else {
        this.title = this.model.title;
        this.showServerNotfiy();
      }
    }, error => {
      console.log('Error service ', error);
    });
  }

  showSuccess() {
    this.notification.template(this.templateSuccess);
  }

  showFailed() {
    this.notification.template(this.templateFailed);
  }

  showServerNotfiy() {
    this.notification.template(this.templateServer);
  }
}
