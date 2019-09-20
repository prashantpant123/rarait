import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../../shared/constants/client-api';
import {GradeCreateModel} from '../../model/exam/grade/grade-create.model';
import {GradeDetailModel} from '../../model/exam/grade/grade-detail.model';
import {GradePageModel} from '../../model/exam/grade/grade-page.model';

@Injectable()
export class GradeService {

  constructor(private http: HttpClient) {
  }

  createGrade(model: GradeCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.GRADE, model);
  }

  getGradeDetail(gradeId: string): Observable<GradeDetailModel> {
    return this.http.get<GradeDetailModel>(ClientApi.GRADE_ID.replace(/{grade_id}/gi, gradeId));
  }

  getGradeList(page: number): Observable<GradePageModel> {
    return this.http.get<GradePageModel>(ClientApi.GRADE + '?page=' + page);
  }
}
