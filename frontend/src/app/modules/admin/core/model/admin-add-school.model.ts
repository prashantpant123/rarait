import {UserCreateModel} from './user-create.model';
import {ContactCreateModel} from './contact-create.model';

export class AdminAddSchoolModel {

  name: string;
  address: string;
  area: string;
  landline: string;
  website: string;
  principal: string;
  registration_no_prefix: string;
  contact: ContactCreateModel;
  user: UserCreateModel;
}
