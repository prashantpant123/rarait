import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../shared/constants/client-api';
import {StaffCreateModel} from '../model/staff/staff-create.model';
import {StaffPageModel} from '../model/staff/staff-page.model';
import {StaffDetailModel} from '../model/staff/staff-detail.model';
import {StaffDropdownModel} from '../model/staff/staff-dropdown.model';
import {AddResponseModel} from '../model/shared/add-response.model';

@Injectable()
export class StaffService {

  constructor(private http: HttpClient) {
  }

  getStaffList(page: number, typeId: number): Observable<StaffPageModel> {
    return this.http.get<StaffPageModel>(ClientApi.STAFF + '?page=' + page +
      (typeId !== 0 ? '&employee_type=' + typeId : ''));
  }

  createNewStaff(staffModel: StaffCreateModel): Observable<AddResponseModel> {
    return this.http.post<AddResponseModel>(ClientApi.STAFF, staffModel);
  }

  updateStaffDetail(staffModel: StaffCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.STAFF_EDIT, staffModel);
  }

  getStaffType(): Observable<StaffDropdownModel[]> {
    return this.http.get<StaffDropdownModel[]>(ClientApi.STAFF_TYPE);
  }

  getStaffDetail(staffId: string): Observable<StaffDetailModel> {
    return this.http.get<StaffDetailModel>(ClientApi.STAFF_ID.replace(/{staff_id}/gi, staffId));
  }
}
