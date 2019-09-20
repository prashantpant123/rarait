import {Component, OnInit} from '@angular/core';

import {routerTransition} from '../../../../router.animations';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {GradeDetailModel} from '../../core/model/exam/grade/grade-detail.model';
import {GradeService} from '../../core/service/exam/grade.service';
import {ClientUrl} from '../../../../shared/constants/client-url';

@Component({
  selector: 'app-grade-list',
  templateUrl: './grade-list.component.html',
  animations: [routerTransition()]
})
export class GradeListComponent implements OnInit {

  gradeList: GradeDetailModel[] = [];
  route: BreadcrumbModel[] = [];

  _loading = true;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  text$ = 'New Grade';
  routeLink$ = '/client/exam/grade/create';

  constructor(private gradeService: GradeService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Grades', ClientUrl.EXAM)
    );
  }

  ngOnInit() {
    this.getGradeList();
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getGradeList();
  }

  private getGradeList() {
    this.gradeService.getGradeList(this._currentPage)
      .subscribe(data => {
        this.gradeList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this._loading = false;
      });
  }

}
