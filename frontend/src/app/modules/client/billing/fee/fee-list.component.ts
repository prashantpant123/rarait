import {Component, OnInit} from '@angular/core';

import {routerTransition} from '../../../../router.animations';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {FeeListModel} from '../../core/model/billing/fee/fee-list.model';
import {FeeService} from '../../core/service/billing/fee.service';

@Component({
  selector: 'app-fee-list',
  templateUrl: './fee-list.component.html',
  animations: [routerTransition()]
})
export class FeeListComponent implements OnInit {

  feeList: FeeListModel[] = [];
  route: BreadcrumbModel[] = [];
  _loading = true;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  text$ = 'Fee Structure';
  routeLink$ = '/client/billing/fee/create';


  constructor(
    private feeService: FeeService
  ) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Fees', ClientUrl.FEE),
      new BreadcrumbModel('Create', ClientUrl.FEE_CREATE)
    );
  }

  ngOnInit() {
    this.getFeeList();
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getFeeList();
  }

  private getFeeList() {
    this.feeService.getFeeList(this._currentPage)
      .subscribe(data => {
        this.feeList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this._loading = false;
      });
  }
}
