import {Component, OnInit} from '@angular/core';

import {AcademicSessionListModel} from '../core/model/academic-session/academic-session-list.model';
import {AcademicSessionService} from '../core/service/academic-session.service';
import {ClientUrl} from '../../../shared/constants/client-url';
import {routerTransition} from '../../../router.animations';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';

@Component({
  selector: 'app-academic-session-list',
  templateUrl: './academic-session-list.component.html',
  animations: [routerTransition()]
})
export class AcademicSessionListComponent implements OnInit {

  academicSessionList: AcademicSessionListModel[] = [];
  route: BreadcrumbModel[] = [];
  text$ = 'New Academic Session';
  routeLink$ = '/client/academic-session/create';

  _loading = true;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;
  _size = 'middle';


  sortValue: string | null = null;
  sortKey: string | null = null;
  filterSession = [{text: 'Yes', value: 'true'}, {text: 'No', value: 'false'}];
  searchSessionList: string[] = [];

  constructor(private academicSessionService: AcademicSessionService) {

    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Academic Session', ClientUrl.ACADEMIC_SESSION)
    );
  }

  ngOnInit() {
    this.getSessionList();
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getSessionList();
  }

  sort(sort: { key: string; value: string }): void {
    this.sortKey = sort.key;
    this.sortValue = sort.value;
    this.getSessionList();
  }

  updateFilter(value: string[]): void {
    this.searchSessionList = value;
    this._currentPage = 1;
    this.getSessionList();
  }

  private getSessionList() {
    this._loading = true;
    this.academicSessionService.getPagedSessionList(this._currentPage, this.sortKey, this.sortValue, this.searchSessionList)
      .subscribe(data => {
        this.academicSessionList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this._loading = false;
      });
  }

}
