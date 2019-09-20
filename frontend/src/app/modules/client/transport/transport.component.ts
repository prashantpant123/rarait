import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

import {routerTransition} from '../../../router.animations';
import {ReadOnlyModel} from '../../../shared/components/read-only/read-only.model';

import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../shared/constants/client-url';
import {TransportService} from '../core/service/transport.service';
import {TransportDetailModel} from '../core/model/transport/transport-detail.model';

@Component({
  selector: 'transport',
  templateUrl: './transport.component.html',
  animations: [routerTransition()]
})
export class TransportComponent implements OnInit {

  title: string;
  contents: ReadOnlyModel[] = [];
  route: BreadcrumbModel[] = [];
  transportId = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private transportService: TransportService
  ) {
    this.transportId = this.activatedRoute.snapshot.paramMap.get('transport_id');

    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Transport', ClientUrl.TRANSPORT),
      new BreadcrumbModel('Detail', ClientUrl.TRANSPORT + '/' + this.transportId + '/detail')
    );
  }

  ngOnInit() {
    this.transportId = this.activatedRoute.snapshot.paramMap.get('transport_id');

    this.transportService.getTransportDetail(this.transportId)
      .subscribe((data: TransportDetailModel) => {
        this.populateInfo(data);
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
  }

  populateInfo(transport: TransportDetailModel) {
    this.title = 'Transport Detail';
    this.contents.push(
      new ReadOnlyModel('Number Plate', transport.number_plate),
      new ReadOnlyModel('Name', transport.name),
    );
  }

}
