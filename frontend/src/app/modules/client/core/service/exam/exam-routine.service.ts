import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../../shared/constants/client-api';
import {ExamRoutineCreateModel} from '../../model/exam/routine/exam-routine-create.model';
import {ExamRoutinePageModel} from '../../model/exam/routine/exam-routine-page.model';
import {ExamRoutineCreateListModel} from '../../model/exam/routine/exam-routine-create-list.model';

@Injectable()
export class ExamRoutineService {

  constructor(private http: HttpClient) {
  }

  getExamRoutineList(examId: number, classId: number): Observable<ExamRoutinePageModel> {
    return this.http.get<ExamRoutinePageModel>(ClientApi.EXAM_ROUTINE + '?class_id=' + classId + '&exam_id=' + examId);
  }

  createNewExamRoutine(examRoutineModel: ExamRoutineCreateListModel): Observable<any> {
    return this.http.post<any>(ClientApi.EXAM_ROUTINE, examRoutineModel);
  }

  updateExamRoutineDetail(examRoutineModel: ExamRoutineCreateModel): Observable<any> {
    return this.http.post<any>(ClientApi.EXAM_ROUTINE_EDIT, examRoutineModel);
  }
}
