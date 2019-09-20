import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';

import {routerTransition} from '../../../router.animations';
import {ClassListModel} from '../core/model/class/class-list.model';
import {ClassService} from '../core/service/class.service';
import {ClientUrl} from '../../../shared/constants/client-url';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';

@Component({
  selector: 'app-class-list',
  templateUrl: './class-list.component.html',
  animations: [routerTransition()]
})
export class ClassListComponent implements OnInit {

  classList: ClassListModel[] = [];
  _loading = true;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  route: BreadcrumbModel[] = [];
  text$ = 'New Class';
  routeLink$ = '/client/class/create';

  sortValue: string | null = null;
  sortKey: string | null = null;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private classService: ClassService
  ) {

    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Classes', ClientUrl.CLASS)
    );
  }

  ngOnInit() {
    this.getClassList();
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getClassList();
  }

  sort(sort: { key: string; value: string }): void {
    this.sortKey = sort.key;
    this.sortValue = sort.value;
    this.getClassList();
  }

  private getClassList() {
    this.classService.getClassList(this._currentPage, this.sortKey, this.sortValue)
      .subscribe(data => {
        this.classList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this._loading = false;
      });
  }

}
