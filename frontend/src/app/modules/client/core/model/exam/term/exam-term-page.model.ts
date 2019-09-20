import {PageModel} from '../../../../../../shared/model/page.model';
import {ExamTermListModel} from './exam-term-list.model';

export class ExamTermPageModel extends PageModel {
  content: ExamTermListModel[] = [];
}
