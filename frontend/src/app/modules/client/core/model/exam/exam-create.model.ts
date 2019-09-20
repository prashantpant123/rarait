export class ExamCreateModel {

  term_id: number;
  start_date: Date;
  end_date: Date;
  weightage: number;
  academic_session_id: number;

  constructor(term_id: number,
              start_date: Date,
              end_date: Date,
              weightage: number,
              academic_session_id: number) {
    this.term_id = term_id;
    this.start_date = start_date;
    this.end_date = end_date;
    this.weightage = weightage;
    this.academic_session_id = academic_session_id;
  }
}
