import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/index';

import {AdminSchoolListModel} from '../model/admin-school-list.model';
import {AdminAddSchoolModel} from '../model/admin-add-school.model';

import {AdminApi} from '../../../../shared/constants/admin-api';
import {AdminSchoolTypeModel} from '../model/admin-school-type.model';
import {AdminSchoolDetailModel} from '../model/admin-school-detail.model';
import {AdminAddSchoolResponseModel} from '../model/admin-add.school-response.model';
import {RegistrationCheckResponseModel} from '../../../client/core/model/shared/registration-check-response.model';
import {ClientApi} from '../../../../shared/constants/client-api';

@Injectable()
export class AdminSchoolService {

  constructor(private http: HttpClient) {
  }

  getSchoolType(): Observable<AdminSchoolTypeModel[]> {
    return this.http.get<AdminSchoolTypeModel[]>(AdminApi.SCHOOL_TYPE);
  }

  getSchoolList(page: number): Observable<AdminSchoolListModel> {
    return this.http.get<AdminSchoolListModel>(AdminApi.SCHOOL + '?page=' + page);
  }

  checkRegistrationPrefix(registrationPrefix: string): Observable<RegistrationCheckResponseModel> {
    console.log('Request to verify');
    return this.http.get<RegistrationCheckResponseModel>(AdminApi.SCHOOL_REGISTRATION_PREFIX_CHECK.replace(/{registration_id}/gi, registrationPrefix));
  }


  createSchool(adminAddSchool: AdminAddSchoolModel): Observable<AdminAddSchoolResponseModel> {
    return this.http.post<AdminAddSchoolResponseModel>(AdminApi.SCHOOL, adminAddSchool);
  }

  updateSchoolStatus(schoolUpdate: AdminAddSchoolModel): Observable<any> {
    return this.http.post(AdminApi.SCHOOL_DETAIL, schoolUpdate);
  }

  getSchoolDetail(institute_id: string): Observable<AdminSchoolDetailModel> {
    return this.http.get<AdminSchoolDetailModel>(AdminApi.SCHOOL_DETAIL.replace(/{institute_id}/gi, institute_id));
  }
}
