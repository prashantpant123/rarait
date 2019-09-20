import {InvoiceListModel} from './invoice-list.model';
import {PageModel} from '../../../../../../shared/model/page.model';

export class InvoicePageModel extends PageModel{

  content: InvoiceListModel[];
}
