import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../shared/constants/client-api';
import {SubjectCreateModel} from '../model/subject/subject-create.model';
import {SubjectPageModel} from '../model/subject/subject-page.model';
import {SubjectDetailModel} from '../model/subject/subject-detail.model';
import {DropdownModel} from '../../../../shared/model/dropdown.model';
import {ExamRoutineSubjectPageModel} from '../model/exam/routine/exam-routine-subject-page.model';

@Injectable()
export class SubjectService {

  constructor(private http: HttpClient) {
  }

  getSubjectList(page: number, classId: number, sortField: string, sortOrder: string): Observable<SubjectPageModel> {
    let params = new HttpParams()
      .append('page', `${page}`)
      .append('class_id', `${classId}`);
    if (sortField !== null) {
      params = params.append('sort_field', sortField);
    }
    if (sortOrder !== null) {
      params = params.append('ascend', sortOrder === 'ascend' ? true.toString() : false.toString());
    }
    return this.http.get<SubjectPageModel>(`${ClientApi.SUBJECT}`, {params});
  }

  createNewSubject(subjectModel: SubjectCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.SUBJECT, subjectModel);
  }

  updateSubjectDetail(subjectModel: SubjectCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.SUBJECT_EDIT, subjectModel);
  }

  getSubjectDetail(subjectId: string): Observable<SubjectDetailModel> {
    return this.http.get<SubjectDetailModel>(ClientApi.SUBJECT_ID.replace(/{subject_id}/gi, subjectId));
  }
}
