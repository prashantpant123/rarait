import {PageModel} from '../../../../../../shared/model/page.model';
import {ExamResultListModel} from './exam-result-list.model';

export class ExamResultPageModel extends PageModel {
  content: ExamResultListModel[];
}
