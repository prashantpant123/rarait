import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {ClientApi} from '../../../../shared/constants/client-api';
import {SchoolBasicInfoModel} from '../model/school/school-basic-info.model';

@Injectable({providedIn: 'root'})
export class HeaderService {

  private schoolInfoSubject: BehaviorSubject<SchoolBasicInfoModel>;
  SCHOOL_INFO = 'education_info';

  constructor(private http: HttpClient) {
    this.schoolInfoSubject = new BehaviorSubject<SchoolBasicInfoModel>(JSON.parse(localStorage.getItem(this.SCHOOL_INFO)));
  }

  schoolBasicInfo(): Observable<SchoolBasicInfoModel> {
    return this.http.get<SchoolBasicInfoModel>(ClientApi.SCHOOL)
      .pipe(map(data => {
        if (data) {
          localStorage.setItem(this.SCHOOL_INFO, JSON.stringify(data));
          this.schoolInfoSubject.next(data);
        }
        return data;
      }));
  }

  getSchoolBasicInfo(): SchoolBasicInfoModel {
    return this.schoolInfoSubject.value;
  }
}
