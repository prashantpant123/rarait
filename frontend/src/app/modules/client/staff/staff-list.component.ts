import {Component, OnInit} from '@angular/core';

import {ClientUrl} from '../../../shared/constants/client-url';
import {StaffListModel} from '../core/model/staff/staff-list.model';
import {StaffService} from '../core/service/staff.service';
import {routerTransition} from '../../../router.animations';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {StaffDropdownModel} from '../core/model/staff/staff-dropdown.model';

@Component({
  selector: 'app-staff-list',
  templateUrl: './staff-list.component.html',
  animations: [routerTransition()]
})
export class StaffListComponent implements OnInit {

  staffList: StaffListModel[] = [];
  staffTypes: StaffDropdownModel[] = [];
  route: BreadcrumbModel[] = [];

  _loading = true;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  staffValue = '0';
  text$ = 'New Staff';
  routeLink$ = '/client/staff/create';
  staffTypeSelectedId: number = 0;

  constructor(
    private staffService: StaffService
  ) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Staff', ClientUrl.STAFF)
    );
  }

  ngOnInit() {
    this.getStaffList();
    this.getStaffType();
  }

  getStaffType() {
    this.staffService.getStaffType()
      .subscribe(data => {
        this.staffTypes = data;
      });
  }

  staffTypeSelectEvent(selectedId: any) {
    if (selectedId !== '0') {
      this.staffTypeSelectedId = selectedId;
      this.getStaffList();
    }
  }

  private getStaffList() {
    this.staffService.getStaffList(this._currentPage, this.staffTypeSelectedId)
      .subscribe(data => {
        this.staffList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this._loading = false;
      }, error => {
        console.log('Error: ', error);
      });
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getStaffList();
  }

}
