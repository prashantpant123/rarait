import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ClientApi} from '../../../../../shared/constants/client-api';
import {InvoiceDetailModel} from '../../model/billing/invoice/invoice-detail.model';
import {InvoiceCreateListModel} from '../../model/billing/invoice/invoice-create-list.model';
import {InvoicePageModel} from '../../model/billing/invoice/invoice-page.model';

@Injectable()
export class InvoiceService {

  constructor(private http: HttpClient) {
  }

  addInvoice(createModel: InvoiceCreateListModel): Observable<any> {
    return this.http.post(ClientApi.INVOICE, createModel);
  }

  getInvoiceList(page: number): Observable<InvoicePageModel> {
    return this.http.get<InvoicePageModel>(ClientApi.INVOICE + '?page=' + page);
  }

  getInvoiceDetail(invoiceId: string): Observable<InvoiceDetailModel> {
    return this.http.get<InvoiceDetailModel>(ClientApi.INVOICE_DETIAL.replace(/{invoice_id}/gi, invoiceId));
  }
}
