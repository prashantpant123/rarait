import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../shared/constants/client-api';
import {StudentCreateModel} from '../model/student/student-create.model';
import {StudentPageModel} from '../model/student/student-page.model';
import {StudentDetailModel} from '../model/student/student-detail.model';
import {AddResponseModel} from '../model/shared/add-response.model';
import {RegistrationCheckResponseModel} from '../model/shared/registration-check-response.model';

@Injectable()
export class StudentService {

  constructor(private http: HttpClient) {
  }

  getStudentList(page: number, classId: number, sortField: string, sortOrder: string): Observable<StudentPageModel> {
    let params = new HttpParams()
      .append('page', `${page}`)
      .append('class_id', `${classId}`);
    if (sortField !== null) {
      params = params.append('sort_field', sortField);
    }
    if (sortOrder !== null) {
      params = params.append('ascend', sortOrder === 'ascend' ? true.toString() : false.toString());
    }
    return this.http.get<StudentPageModel>(ClientApi.STUDENT, {params});
  }

  searchStudent(page: number, classId: number, searchParam: string , searchValue: string): Observable<StudentPageModel> {
    const params = new HttpParams()
      .append('page', `${page}`)
      .append('class_id', `${classId}`)
      .append('search_param', searchParam)
      .append('search_value', searchValue);
    return this.http.get<StudentPageModel>(ClientApi.STUDENT_SEARCH, {params});
  }


  checkRegistrationNumber(registrationNumber: string): Observable<RegistrationCheckResponseModel> {
    return this.http.get<RegistrationCheckResponseModel>(ClientApi.STUDENT_REGISTRATION_CHECK.replace(/{registration_number}/gi, registrationNumber));
  }

  createNewStudent(staffModel: StudentCreateModel): Observable<AddResponseModel> {
    return this.http.post<AddResponseModel>(ClientApi.STUDENT, staffModel);
  }

  updateStudentDetail(staffModel: StudentCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.STUDENT_EDIT, staffModel);
  }

  getStudentDetail(studentId: string): Observable<StudentDetailModel> {
    return this.http.get<StudentDetailModel>(ClientApi.STUDENT_ID.replace(/{student_id}/gi, studentId));
  }
}
