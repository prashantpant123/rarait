import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UtilityApi} from '../constants/utility.api';
import {Observable} from 'rxjs';

import {DropdownModel} from '../model/dropdown.model';

@Injectable()
export class BloodGroupService {

  constructor(private http: HttpClient) {
  }

  getAllBloodGroup(): Observable<DropdownModel[]> {
    return this.http.get<DropdownModel[]>(UtilityApi.BLOOD_GROUP);
  }
}
