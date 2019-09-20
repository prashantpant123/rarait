export class ClassCreateModel {

  department: number;
  name: string;
  code: string;

  constructor(department: number, name: string, code: string) {
    this.department = department;
    this.name = name;
    this.code = code;
  }
}
