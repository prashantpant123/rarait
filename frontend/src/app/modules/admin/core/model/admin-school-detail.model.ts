import {ContactCreateModel} from './contact-create.model';
import {UserCreateModel} from './user-create.model';

export class AdminSchoolDetailModel {
  name: string;
  address: string;
  code: string;
  landline: string;
  website: string;
  principal: string;
  logo_path: string;

  contact: ContactCreateModel;
  // users: UserCreateModel[];
  user: string;

  registration_prefix: string;
}
