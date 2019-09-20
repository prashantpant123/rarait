import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../shared/constants/client-api';
import {AcademicSessionModel} from '../model/academic-session/academic-session.model';
import {AcademicSessionPageModel} from '../model/academic-session/academic-session-page.model';
import {AcademicSessionDetailModel} from '../model/academic-session/academic-session-detail.model';
import {AcademicSessionDropdownModel} from '../model/academic-session/academic-session-dropdown.model';

@Injectable()
export class AcademicSessionService {

  constructor(private http: HttpClient) {
  }

  getPagedSessionList(page: number, sortField: string, sortOrder: string, sessionList: string[]): Observable<AcademicSessionPageModel> {
    let params = new HttpParams()
      .append('page', `${page}`);
    if (sortField !== null) {
      params = params.append('sort_field', sortField);
    }
    if (sortOrder !== null) {
      params = params.append('ascend', sortOrder === 'ascend' ? true.toString() : false.toString());
    }
    sessionList.forEach(data => {
      params = params.append('session', data);
    });
    return this.http.get<AcademicSessionPageModel>(`${ClientApi.ACADEMIC_SESSION}`, {params});
  }

  createNewSession(academicSession: AcademicSessionModel): Observable<any> {
    return this.http.post<any>(ClientApi.ACADEMIC_SESSION, academicSession);
  }

  updateSessionDetail(academicSession: AcademicSessionModel): Observable<any> {
    return this.http.post<any>(ClientApi.ACADEMIC_SESSION_EDIT, academicSession);
  }

  getSessionDetail(sessionId: string): Observable<AcademicSessionDetailModel> {
    return this.http.get<AcademicSessionDetailModel>(ClientApi.ACADEMIC_SESSION_ID.replace(/{academic_session_id}/gi, sessionId));
  }

  getSessionList(): Observable<AcademicSessionDropdownModel[]> {
    return this.http.get<AcademicSessionDropdownModel[]>(ClientApi.ACADEMIC_SESSION + '/list');
  }
}
