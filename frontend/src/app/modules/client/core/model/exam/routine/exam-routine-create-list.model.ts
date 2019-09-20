import {ExamRoutineCreateModel} from './exam-routine-create.model';

export class ExamRoutineCreateListModel {
  exam_id: number;
  class_id: number;
  data: ExamRoutineCreateModel[] = [];
}
