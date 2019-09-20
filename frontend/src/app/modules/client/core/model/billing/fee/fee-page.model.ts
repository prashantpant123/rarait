import {PageModel} from '../../../../../../shared/model/page.model';
import {FeeListModel} from './fee-list.model';

export class FeePageModel extends PageModel{
  content: FeeListModel[];
}
