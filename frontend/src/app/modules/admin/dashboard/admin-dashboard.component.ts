import {Component, OnInit} from '@angular/core';
import {routerTransition} from '../../../router.animations';
import {ReadOnlyService} from '../../../shared/components/read-only/read-only.service';
import {ReadOnlyModel} from '../../../shared/components/read-only/read-only.model';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss'],
  animations: [routerTransition()]
})
export class AdminDashboardComponent implements OnInit {
  public alerts: Array<any> = [];
  public sliders: Array<any> = [];

  constructor() {
    this.sliders.push(
      {
        imagePath: 'assets/images/slider1.jpg',
        label: 'First slide label',
        text:
          'Just a welcome page.'
      },
      {
        imagePath: 'assets/images/slider2.jpg',
        label: 'Second slide label',
        text: 'Another page!'
      },
      {
        imagePath: 'assets/images/slider3.jpg',
        label: 'Third slide label',
        text:
          'Last page/'
      }
    );
  }

  ngOnInit() {
  }

  public closeAlert(alert: any) {
    const index: number = this.alerts.indexOf(alert);
    this.alerts.splice(index, 1);
  }
}
