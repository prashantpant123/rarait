import {Component, OnInit} from '@angular/core';

import {routerTransition} from '../../../../router.animations';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {TransportRouteListModel} from '../../core/model/transport/transport-route-list.model';
import {TransportRouteService} from '../../core/service/transport-route.service';
import {SubjectListModel} from '../../core/model/subject/subject-list.model';

@Component({
  selector: 'app-transport-route-list',
  templateUrl: './transport-route-list.component.html',
  animations: [routerTransition()]
})
export class TransportRouteListComponent implements OnInit {

  transportRouteList: TransportRouteListModel[] = [];
  subjectList: SubjectListModel[];
  route: BreadcrumbModel[] = [];
  _loading = true;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  text$ = 'Transport Route';
  routeLink$ = '/client/transport/route/create';

  constructor(
    private transportRouteService: TransportRouteService
  ) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Transport Route', ClientUrl.TRANSPORT_ROUTE),
    );
  }

  ngOnInit() {
    this.getTransportRoutes();
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getTransportRoutes();
  }

  private getTransportRoutes() {
    this.transportRouteService.getTransportRouteList(this._currentPage)
      .subscribe(data => {
        this.transportRouteList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this._loading = false;
      });
  }

}
