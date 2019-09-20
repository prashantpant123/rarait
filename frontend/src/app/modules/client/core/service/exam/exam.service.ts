import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../../shared/constants/client-api';
import {ExamCreateModel} from '../../model/exam/exam-create.model';
import {ExamPageModel} from '../../model/exam/exam-page.model';
import {ExamDetailModel} from '../../model/exam/exam-detail.model';
import {ExamDropdownModel} from '../../model/exam/exam-dropdown.model';

@Injectable()
export class ExamService {

  constructor(private http: HttpClient) {
  }

  getExamList(page: number): Observable<ExamPageModel> {
    return this.http.get<ExamPageModel>(ClientApi.EXAM + '?page=' + page);
  }

  createNewExam(examModel: ExamCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.EXAM, examModel);
  }

  updateExamDetail(examModel: ExamCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.EXAM_EDIT, examModel);
  }

  getExamDetail(examId: string): Observable<ExamDetailModel> {
    return this.http.get<ExamDetailModel>(ClientApi.EXAM_ID.replace(/{exam_id}/gi, examId));
  }

  getExamDropdown(): Observable<ExamDropdownModel[]> {
    return this.http.get<ExamDropdownModel[]>(ClientApi.EXAM + '/list');
  }

  getExamAndSessionDropdown(): Observable<ExamDropdownModel[]> {
    return this.http.get<ExamDropdownModel[]>(ClientApi.EXAM_SESSION);
  }
}
