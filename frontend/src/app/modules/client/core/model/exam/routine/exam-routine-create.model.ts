export class ExamRoutineCreateModel {
  id: number;
  subject_id: number;
  exam_date: Date;
  start_time: string;
  end_time: string;
  remarks: string;
  full_mark: number;
  pass_mark: number;

  constructor(id: number,
              subject_id: number,
              exam_date: Date,
              start_time: string,
              end_time: string,
              remarks: string,
              full_mark: number,
              pass_mark: number) {
    this.id = id;
    this.subject_id = subject_id;
    this.exam_date = exam_date;
    this.start_time = start_time;
    this.end_time = end_time;
    this.remarks = remarks;
    this.full_mark = full_mark;
    this.pass_mark = pass_mark;
  }
}
