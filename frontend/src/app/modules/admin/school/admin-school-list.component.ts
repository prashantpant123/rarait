import {Component, OnInit} from '@angular/core';
import {routerTransition} from '../../../router.animations';
import {AdminSchoolService} from '../core/service/admin-school.service';
import {AdminURL} from '../../../shared/constants/admin-url';
import {SchoolInfoModel} from '../core/model/school-info.model';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';

/*
For pagination info:
* https://michaelbromley.github.io/ngx-pagination/#/
 */
@Component({
  selector: 'app-admin-school-list',
  templateUrl: './admin-school-list.component.html',
  animations: [routerTransition()]
})
export class AdminSchoolListComponent implements OnInit {

  route: BreadcrumbModel[] = [];
  schoolList: SchoolInfoModel[] = [];

  _loading = true;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  routeLink$ = '/admin/school/create';
  text$ = 'New School';

  constructor(private schoolService: AdminSchoolService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', AdminURL.DASHBOARD),
      new BreadcrumbModel('School List', AdminURL.SCHOOL)
    );
  }

  ngOnInit() {
    this.getSchoolList();
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getSchoolList();
  }

  private getSchoolList() {
    this.schoolService.getSchoolList(this._currentPage)
      .subscribe(data => {
        this.schoolList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this._loading = false;
      });
  }

  onDeleteConfirm() {
    console.log('Delete test confirm');
  }

  onDeleteCancel() {
    console.log('Delete canceled');
  }

}
