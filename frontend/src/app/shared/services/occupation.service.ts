import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UtilityApi} from '../constants/utility.api';
import {OccupationListModel} from '../model/occupation-list.model';
import {Observable} from 'rxjs';

@Injectable()
export class OccupationService {

  constructor(private http: HttpClient) {
  }

  getAllOccupations(): Observable<OccupationListModel[]> {
    return this.http.get<OccupationListModel[]>(UtilityApi.OCCUPATION);
  }

}
