import {PageModel} from '../../../../../shared/model/page.model';
import {ExamListModel} from './exam-list.model';

export class ExamPageModel extends PageModel {
  content: ExamListModel[];
}
