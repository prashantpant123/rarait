import {Component, OnInit} from '@angular/core';

import {routerTransition} from '../../../router.animations';
import {ClientUrl} from '../../../shared/constants/client-url';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {TransportService} from '../core/service/transport.service';
import {TransportListModel} from '../core/model/transport/transport-list.model';

@Component({
  selector: 'app-transport-list',
  templateUrl: './transport-list.component.html',
  animations: [routerTransition()]
})
export class TransportListComponent implements OnInit {

  transportList: TransportListModel[] = [];
  route: BreadcrumbModel[] = [];
  _loading = true;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  text$ = 'New Transport';
  routeLink$ = '/client/transport/create';

  constructor(
    private transportService: TransportService
  ) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Transport', ClientUrl.TRANSPORT),
    );
  }

  ngOnInit() {
    this.getTransports();
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getTransports();
  }

  private getTransports() {
    this.transportService.getTransportList(this._currentPage)
      .subscribe(data => {
        this.transportList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this._loading = false;
      });
  }
}
