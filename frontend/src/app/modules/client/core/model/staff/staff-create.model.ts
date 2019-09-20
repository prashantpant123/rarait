export class StaffCreateModel {
  employee_id: string;
  first_name: string;
  last_name: string;
  date_of_birth: Date;
  gender_id: number;
  current_address: string;
  permanent_address: string;
  joining_date: Date;
  primary_contact: string;
  secondary_contact: string;
  type_id: number;
  qualification: string;
  designation: string;
  blood_group_id: number;
  nationality_id: number;
  marital_status: number;
  experience_summary: string;

  constructor(employee_id: string,
              first_name: string,
              last_name: string,
              date_of_birth: Date,
              gender_id: number,
              current_address: string,
              permanent_address: string,
              joining_date: Date,
              primary_contact: string,
              secondary_contact: string,
              type_id: number,
              qualification: string,
              designation: string,
              blood_group: number,
              nationality_id: number,
              experience_summary: string,
              marital_status: number) {
    this.employee_id = employee_id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.date_of_birth = date_of_birth;
    this.gender_id = gender_id;
    this.current_address = current_address;
    this.permanent_address = permanent_address;
    this.joining_date = joining_date;
    this.primary_contact = primary_contact;
    this.secondary_contact = secondary_contact;
    this.type_id = type_id;
    this.qualification = qualification;
    this.designation = designation;
    this.blood_group_id = blood_group;
    this.nationality_id = nationality_id;
    this.marital_status = marital_status;
    this.experience_summary = experience_summary;
  }
}
