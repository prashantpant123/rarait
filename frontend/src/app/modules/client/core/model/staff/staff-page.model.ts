import {StaffListModel} from './staff-list.model';
import {PageModel} from '../../../../../shared/model/page.model';

export class StaffPageModel extends PageModel {
  content: StaffListModel[];
}
