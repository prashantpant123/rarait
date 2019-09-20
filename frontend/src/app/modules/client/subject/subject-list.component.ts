import {Component, OnInit} from '@angular/core';
import {routerTransition} from '../../../router.animations';
import {ClientUrl} from '../../../shared/constants/client-url';

import {SubjectListModel} from '../core/model/subject/subject-list.model';
import {SubjectService} from '../core/service/subject.service';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {ClassService} from '../core/service/class.service';
import {ClassDropdownModel} from '../core/model/class/class-dropdown.model';

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  animations: [routerTransition()]
})
export class SubjectListComponent implements OnInit {

  subjectList: SubjectListModel[] = [];
  route: BreadcrumbModel[] = [];
  classList: ClassDropdownModel[] = [];

  _loading = true;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  text$ = 'New Subject';
  routeLink$ = '/client/subject/create';
  classValue = '0';
  classSelectedId: number = 0;

  sortValue: string | null = null;
  sortKey: string | null = null;

  constructor(
    private subjectService: SubjectService,
    private classService: ClassService
  ) {

    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Subjects', ClientUrl.SUBJECT)
    );
  }

  ngOnInit() {
    this.getClassList();
    this.getSubjectList();
  }

  private getClassList() {
    this.classService.getClassDropdown()
      .subscribe(data => {
        this.classList = data;
      }, error => {
        console.log('Error: ', error);
      });
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getSubjectList();
  }

  sort(sort: { key: string; value: string }): void {
    this.sortKey = sort.key;
    this.sortValue = sort.value;
    this.getSubjectList();
  }

  private getSubjectList() {
    this.subjectService.getSubjectList(this._currentPage, this.classSelectedId, this.sortKey, this.sortValue)
      .subscribe(data => {
        this.subjectList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this._loading = false;
      });
  }

  classSelectEvent(selectedId: any) {
    if (selectedId !== 0) {
      this.classSelectedId = selectedId;
      this.getSubjectList();
    }
  }

}
