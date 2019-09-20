import {Component, OnInit} from '@angular/core';

import {routerTransition} from '../../../../router.animations';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {ProgressReportListModel} from '../../core/model/exam/progress-report/progress-report-list.model';


@Component({
  selector: 'app-progress-report-list',
  templateUrl: './progress-report-list.component.html',
  animations: [routerTransition()]
})
export class ProgressReportListComponent implements OnInit {
  links: Object[] = [];
  progressReportList: ProgressReportListModel[] = [];

  constructor() {
    this.links.push(
      {path: ClientUrl.DASHBOARD, text: 'Dashboard'},
      {path: ClientUrl.EXAM, text: 'Exam'},
      {path: ClientUrl.EXAM_RESULT, text: 'Progress Report'},
    );
  }

  ngOnInit() {
  }

}
