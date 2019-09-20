import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UtilityApi} from '../constants/utility.api';
import {DropdownModel} from '../model/dropdown.model';
import {Observable} from 'rxjs';

@Injectable()
export class MaritalStatusService {

  constructor(private http: HttpClient) {
  }

  getAllMaritalStatus(): Observable<DropdownModel[]> {
    return this.http.get<DropdownModel[]>(UtilityApi.MARITAL_STATUS);
  }
}
