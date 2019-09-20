import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ClassPageModel} from '../model/class/class-page.model';
import {ClientApi} from '../../../../shared/constants/client-api';
import {DashboardModel} from '../model/dashboard.model';

@Injectable()
export class DashboardService {
  /*Get summary data like school total students etc*/

  constructor(private http: HttpClient) {
  }

  getSummary(): Observable<DashboardModel> {
    return this.http.get<DashboardModel>(ClientApi.SUMMARY);
  }

}
