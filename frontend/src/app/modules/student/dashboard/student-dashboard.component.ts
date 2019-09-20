import {Component, OnInit} from '@angular/core';

import {routerTransition} from '../../../router.animations';
import {StudentDashboardService} from '../core/service/student-dashboard.service';

@Component({
  selector: 'app-student-dashboard',
  templateUrl: './student-dashboard.component.html',
  animations: [routerTransition()]
})
export class StudentDashboardComponent implements OnInit {

  loading = true;
  totalNotification: number;

  constructor(private dashboardService: StudentDashboardService) {
  }

  ngOnInit() {
    this.getDashboardSumamry();
  }

  private getDashboardSumamry() {
    this.loading = false;
  }

}
