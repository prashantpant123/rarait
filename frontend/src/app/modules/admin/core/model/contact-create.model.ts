export class ContactCreateModel {

  full_name: string;
  mobile_number: string;
  email_id: string;
  landline_number: string;
  designation: string;

  constructor(full_name: string,
              mobile_number: string,
              email_id: string,
              landline_number: string,
              designation: string) {
    this.full_name = full_name;
    this.mobile_number = mobile_number;
    this.email_id = email_id;
    this.landline_number = landline_number;
    this.designation = designation;
  }

}
