import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

import {routerTransition} from '../../../../router.animations';
import {ReadOnlyModel} from '../../../../shared/components/read-only/read-only.model';

import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {TransportRouteService} from '../../core/service/transport-route.service';
import {TransportRouteDetailModel} from '../../core/model/transport/transport-route-detail.model';

@Component({
  selector: 'transport-route',
  templateUrl: './transport-route.component.html',
  animations: [routerTransition()]
})
export class TransportRouteComponent implements OnInit {

  title: string;
  contents: ReadOnlyModel[] = [];
  route: BreadcrumbModel[] = [];
  transportRouteId = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private transportRouteService: TransportRouteService
  ) {
    this.transportRouteId = this.activatedRoute.snapshot.paramMap.get('transport_route_id');

    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Transport Route', ClientUrl.TRANSPORT_ROUTE),
      new BreadcrumbModel('Detail', ClientUrl.TRANSPORT_ROUTE + '/' + this.transportRouteId + '/detail')
    );
  }

  ngOnInit() {
    this.transportRouteId = this.activatedRoute.snapshot.paramMap.get('transport_route_id');

    this.transportRouteService.getTransportRouteDetail(this.transportRouteId)
      .subscribe((data: TransportRouteDetailModel) => {
        this.populateInfo(data);
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
  }

  populateInfo(transport: TransportRouteDetailModel) {
    this.title = 'Transport Route Detail';
    this.contents.push(
      new ReadOnlyModel('Number Plate', transport.number_plate),
      new ReadOnlyModel('Route', transport.route_path),
      new ReadOnlyModel('Pickup Time', transport.pickup_time),
      new ReadOnlyModel('Drop Time', transport.drop_time),
    );
  }

}
