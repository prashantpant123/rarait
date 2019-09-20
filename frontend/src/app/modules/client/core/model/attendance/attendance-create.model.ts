export class AttendanceCreateModel {
  id: number;
  student_id: number;
  status: number;
  remarks: string;

  constructor(id: number,
              student_id: number,
              status: number,
              remarks: string) {
    this.id = id;
    this.student_id = student_id;
    this.status = status;
    this.remarks = remarks;
  }
}
