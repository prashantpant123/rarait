import {AttendanceCreateModel} from './attendance-create.model';

export class AttendanceCreateListModel {

  class_id: number;
  attendance_date: Date;
  data: AttendanceCreateModel[] = [];
}
