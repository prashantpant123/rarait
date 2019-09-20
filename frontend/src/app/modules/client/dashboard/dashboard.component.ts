import {Component, OnInit} from '@angular/core';
import {timer} from 'rxjs/observable/timer';

import {routerTransition} from '../../../router.animations';
import {DashboardService} from '../core/service/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  animations: [routerTransition()]
})
export class DashboardComponent implements OnInit {

  loading = true;
  totalStudent: number;
  totalTeacher: number;

  constructor(private dashboardService: DashboardService) {
  }

  ngOnInit() {
    this.getDashboardSumamry();
  }

  private getDashboardSumamry() {
    this.dashboardService.getSummary()
      .subscribe(data => {
        this.totalStudent = data.total_student;
        this.totalTeacher = data.total_teacher;
      });
    // this.totalStudent = '10';
    // this.totalTeacher = '5';
    this.loading = false;
  }

}
