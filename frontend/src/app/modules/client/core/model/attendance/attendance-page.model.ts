import {PageModel} from '../../../../../shared/model/page.model';
import {AttendanceListModel} from './attendance-list.model';

export class AttendancePageModel extends PageModel {

  content: AttendanceListModel[] = [];
}
