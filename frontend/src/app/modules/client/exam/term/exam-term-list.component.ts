import {Component, OnInit} from '@angular/core';

import {routerTransition} from '../../../../router.animations';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {ExamTermService} from '../../core/service/exam/exam-term.service';
import {ExamTermListModel} from '../../core/model/exam/term/exam-term-list.model';

@Component({
  selector: 'app-exam-term-list',
  templateUrl: './exam-term-list.component.html',
  animations: [routerTransition()]
})
export class ExamTermListComponent implements OnInit {

  route: BreadcrumbModel[] = [];
  termList: ExamTermListModel[] = [];

  _loading = true;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  text$ = 'New Term';
  routeLink$ = '/client/exam/term/create';

  constructor(private examTermService: ExamTermService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Exam', ClientUrl.EXAM),
      new BreadcrumbModel('Terms', ClientUrl.TERM)
    );
  }

  ngOnInit() {
    this.getExamTermList();
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getExamTermList();
  }

  private getExamTermList() {
    this.examTermService.getExamTermList(this._currentPage)
      .subscribe(data => {
        this.termList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this._loading = false;
      });
  }
}
