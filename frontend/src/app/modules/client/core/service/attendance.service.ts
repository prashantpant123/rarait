import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../shared/constants/client-api';
import {AttendancePageModel} from '../model/attendance/attendance-page.model';
import {AttendanceDropdownModel} from '../model/attendance/attendance-dropdown.model';
import {AttendanceCreateListModel} from '../model/attendance/attendance-create-list.model';

@Injectable()
export class AttendanceService {

  constructor(private http: HttpClient) {
  }

  getAttendanceList(page: number, classId: number, attendanceDate: string): Observable<AttendancePageModel> {
    return this.http.get<AttendancePageModel>(ClientApi.ATTENDANCE + '?page=' + page
      + '&class_id=' + classId + '&attendance=' + attendanceDate);
  }

  updateAttendanceRecord(attendanceList: AttendanceCreateListModel): Observable<any> {
    return this.http.post<any>(ClientApi.ATTENDANCE, attendanceList);
  }

  getAttendanceStatusList(): Observable<AttendanceDropdownModel[]> {
    return this.http.get<AttendanceDropdownModel[]>(ClientApi.ATTENDANCE + '/type');
  }
}
