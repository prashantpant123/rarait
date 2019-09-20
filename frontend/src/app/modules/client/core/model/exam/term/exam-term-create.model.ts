export class ExamTermCreateModel {
  name: string;
  weightage: number;

  constructor(name: string, weightage: number) {
    this.name = name;
    this.weightage = weightage;
  }
}
