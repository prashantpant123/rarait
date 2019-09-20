import {Injectable} from '@angular/core';
import {PageModel} from '../../../../../shared/model/page.model';
import {TransportListModel} from './transport-list.model';

export class TransportPageModel extends PageModel {
  content: TransportListModel[];
}
