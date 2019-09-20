export class SubjectCreateModel {

  level_id: number;
  name: string;
  code: string;
  description: string;
  full_mark: number;
  pass_mark: number;
  optional: boolean;
  practical: boolean;
  practical_full_mark: number;
  practical_pass_mark: number;

  constructor(level_id: number,
              name: string,
              code: string,
              description: string,
              full_mark: number,
              pass_mark: number,
              optional: boolean,
              practical: boolean,
              practical_full_mark: number,
              practical_pass_mark: number) {
    this.level_id = level_id;
    this.name = name;
    this.code = code;
    this.description = description;
    this.full_mark = full_mark;
    this.pass_mark = pass_mark;
    this.optional = optional;
    this.practical = practical;
    this.practical_full_mark = practical_full_mark;
    this.practical_pass_mark = practical_pass_mark;
  }
}
