export class AcademicSessionModel {

  name: string;
  start_date_ad: Date;
  end_date_ad: Date;
  current_session: boolean;

  constructor(name: string, start_date_ad: Date, end_date_ad: Date, current_session: boolean) {
    this.name = name;
    this.start_date_ad = start_date_ad;
    this.end_date_ad = end_date_ad;
    this.current_session = current_session;
  }
}
