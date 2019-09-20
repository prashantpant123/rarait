import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../../shared/constants/client-api';
import {ExamResultCreateModel} from '../../model/exam/result/exam-result-create.model';
import {ExamResultPageModel} from '../../model/exam/result/exam-result-page.model';

@Injectable()
export class ExamResultService {

  constructor(private http: HttpClient) {
  }

  getExamResultList(page: number): Observable<ExamResultPageModel> {
    return this.http.get<ExamResultPageModel>(ClientApi.EXAM_RESULT + '?page=' + page);
  }

  createNewExamResult(examResultModel: ExamResultCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.EXAM, examResultModel);
  }

  updateExamResultDetail(examResultModel: ExamResultCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.EXAM_RESULT_EDIT, examResultModel);
  }

  getSubjectDetail(subjectId: number) {

  }
}
