import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../shared/constants/client-api';
import {TransportRoutePageModel} from '../model/transport/transport-route-page.model';
import {TransportRouteCreateModel} from '../model/transport/transport-route-create.model';
import {TransportRouteDetailModel} from '../model/transport/transport-route-detail.model';

@Injectable()
export class TransportRouteService {

  constructor(private http: HttpClient) {
  }

  getTransportRouteList(page: number): Observable<TransportRoutePageModel> {
    return this.http.get<TransportRoutePageModel>(ClientApi.TRANSPORT_ROUTE + '?page=' + page);
  }

  newTransportRoute(transportModel: TransportRouteCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.TRANSPORT_ROUTE, transportModel);
  }

  updateTransportRoute(transportModel: TransportRouteCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.TRANSPORT_ROUTE, transportModel);
  }

  getTransportRouteDetail(routeId: string): Observable<TransportRouteDetailModel> {
    return this.http.get<TransportRouteDetailModel>(ClientApi.TRANSPORT_ROUTE_ID.replace(/{route_id}/gi, routeId));
  }
}
