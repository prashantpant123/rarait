import {Component, OnInit} from '@angular/core';

import {routerTransition} from '../../../../router.animations';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {ReadOnlyModel} from '../../../../shared/components/read-only/read-only.model';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';

@Component({
  selector: 'app-exam-result-list',
  templateUrl: './exam-result-list.component.html',
  animations: [routerTransition()]
})
export class ExamResultComponent implements OnInit {
  title: string;
  contents: ReadOnlyModel[] = [];
  route: BreadcrumbModel[] = [];

  constructor() {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Exams', ClientUrl.EXAM),
      new BreadcrumbModel('Result', ClientUrl.EXAM_RESULT),
    );
  }

  ngOnInit() {
  }

}
