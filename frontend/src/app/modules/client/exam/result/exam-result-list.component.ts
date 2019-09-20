import {Component, OnInit} from '@angular/core';

import {routerTransition} from '../../../../router.animations';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {ExamResultListModel} from '../../core/model/exam/result/exam-result-list.model';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ExamResultService} from '../../core/service/exam/exam-result.service';

@Component({
  selector: 'app-exam-result-list',
  templateUrl: './exam-result-list.component.html',
  animations: [routerTransition()]
})
export class ExamResultListComponent implements OnInit {

  route: BreadcrumbModel[] = [];
  examResultList: ExamResultListModel[] = [];

  submitting = false;
  _loading = false;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  text$ = 'Exam Result';
  routeLink$ = '/client/exam/result/create';

  constructor(private examResultService: ExamResultService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Exams', ClientUrl.EXAM),
      new BreadcrumbModel('Marksheet', ClientUrl.EXAM_RESULT)
    );
  }

  ngOnInit() {
    this.getExamResultList();
    // this.getSubjectInfo();
  }

  private getSubjectInfo() {

  }

  private getExamResultList() {
    this.examResultService.getExamResultList(this._currentPage)
      .subscribe(data => {
        this.examResultList = data.content;
      });
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getExamResultList();
  }

}
