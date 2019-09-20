import {Injectable} from '@angular/core';

import {PageModel} from '../../../../../shared/model/page.model';
import {TransportRouteListModel} from './transport-route-list.model';

@Injectable()
export class TransportRoutePageModel extends PageModel {

  content: TransportRouteListModel[];
}
