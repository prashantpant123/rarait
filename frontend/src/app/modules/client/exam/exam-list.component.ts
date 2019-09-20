import {Component, OnInit} from '@angular/core';

import {routerTransition} from '../../../router.animations';
import {ClientUrl} from '../../../shared/constants/client-url';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {ExamService} from '../core/service/exam/exam.service';
import {ExamListModel} from '../core/model/exam/exam-list.model';

@Component({
  selector: 'app-exam-list',
  templateUrl: './exam-list.component.html',
  animations: [routerTransition()]
})
export class ExamListComponent implements OnInit {

  examList: ExamListModel[] = [];
  route: BreadcrumbModel[] = [];

  _loading = true;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  text$ = 'New Exam Schedule';
  routeLink$ = '/client/exam/create';
  excelRouteLink$ = '/client/exam/excel';

  constructor(
    private examService: ExamService
  ) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Exams', ClientUrl.EXAM)
    );
  }

  ngOnInit() {
    this.getExamList();
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getExamList();
  }

  private getExamList() {
    this.examService.getExamList(this._currentPage)
      .subscribe(data => {
        this.examList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this._loading = false;
      });
  }

}
