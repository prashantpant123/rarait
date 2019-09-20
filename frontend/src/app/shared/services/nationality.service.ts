import {Injectable} from '@angular/core';
import {UtilityApi} from '../constants/utility.api';
import {DropdownModel} from '../model/dropdown.model';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class NationalityService {

  constructor(private http: HttpClient) {
  }

  getAllNationality(): Observable<DropdownModel[]> {
    return this.http.get<DropdownModel[]>(UtilityApi.NATIONALITY);
  }
}
