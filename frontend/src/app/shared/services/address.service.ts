import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {AddressListModel} from '../model/address-list.model';
import {HttpClient} from '@angular/common/http';

import {UtilityApi} from '../constants/utility.api';

@Injectable()
export class AddressService {

  constructor(private http: HttpClient) {
  }

  public getAllState(): Observable<AddressListModel[]> {
    return this.http.get<AddressListModel[]>(UtilityApi.ADDRESS_STATE);
  }

  public getAllDistrict(stateId: number): Observable<AddressListModel[]> {
    return this.http.get<AddressListModel[]>(UtilityApi.ADDRESS_DISTRICT);
  }

  public getAllMunicipality(districtId: number): Observable<AddressListModel[]> {
    return this.http.get<AddressListModel[]>(UtilityApi.ADDRESS_MUNICIPALITY);
  }
}
