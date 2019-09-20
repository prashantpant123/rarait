import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {ClientApi} from '../../../../../shared/constants/client-api';
import {DropdownModel} from '../../../../../shared/model/dropdown.model';
import {ExamTermCreateModel} from '../../model/exam/term/exam-term-create.model';
import {ExamTermPageModel} from '../../model/exam/term/exam-term-page.model';

@Injectable()
export class ExamTermService {

  constructor(private http: HttpClient) {
  }

  createNewExamTerm(termModel: ExamTermCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.EXAM_TERM, termModel);
  }

  getExamTermList(page: number): Observable<ExamTermPageModel> {
    return this.http.get<ExamTermPageModel>(ClientApi.EXAM_TERM + '?page=' + page);
  }

  getExamTermDropdown(): Observable<DropdownModel[]> {
    return this.http.get<DropdownModel[]>(ClientApi.EXAM_TERM_LIST);
  }

}
