import {PageModel} from '../../../../../shared/model/page.model';
import {StudentListModel} from './student-list.model';

export class StudentPageModel extends PageModel {
  content: StudentListModel[];
}
