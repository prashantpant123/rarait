import {SchoolInfoModel} from './school-info.model';

export class AdminSchoolListModel {
  total_data: number;
  total_page: number;
  current_page: number;
  content: SchoolInfoModel[];
}
