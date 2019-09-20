import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../shared/constants/client-api';
import {TransportListModel} from '../model/transport/transport-list.model';
import {TransportCreateModel} from '../model/transport/transport-create.model';
import {TransportPageModel} from '../model/transport/transport-page.model';
import {TransportDropdownModel} from '../model/transport/transport-dropdown.model';
import {TransportDetailModel} from '../model/transport/transport-detail.model';

@Injectable()
export class TransportService {

  constructor(private http: HttpClient) {
  }

  getTransportList(page: number): Observable<TransportPageModel> {
    return this.http.get<TransportPageModel>(ClientApi.TRANSPORT + '?page=' + page);
  }

  createNewTransport(transportModel: TransportCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.TRANSPORT, transportModel);
  }

  updateTransportDetail(transportModel: TransportCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.TRANSPORT_EDIT, transportModel);
  }

  getTransportDropdown(): Observable<TransportDropdownModel[]> {
    return this.http.get<TransportDropdownModel[]>(ClientApi.TRANSPORT_LIST);
  }

  getTransportDetail(transportId: string): Observable<TransportDetailModel> {
    return this.http.get<TransportDetailModel>(ClientApi.TRANSPORT_ID.replace(/{transport_id}/gi, transportId));
  }
}
