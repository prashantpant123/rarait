export class GradeCreateModel {
  value: string;
  low_mark: number;
  high_mark: number;

  constructor(value: string, low_mark: number, high_mark: number) {
    this.value = value;
    this.low_mark = low_mark;
    this.high_mark = high_mark;
  }
}
