import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {FeePageModel} from '../../model/billing/fee/fee-page.model';
import {ClientApi} from '../../../../../shared/constants/client-api';
import {FeeCreateModel} from '../../model/billing/fee/fee-create.model';
import {FeeDetailModel} from '../../model/billing/fee/fee-detail.model';

@Injectable()
export class FeeService {

  constructor(private http: HttpClient) {
  }

  getFeeList(page: number): Observable<FeePageModel> {
    return this.http.get<FeePageModel>(ClientApi.FEES + '?page=' + page);
  }

  addFee(createModel: FeeCreateModel): Observable<any> {
    return this.http.post(ClientApi.FEES, createModel);
  }

  getFeeDetail(feeId: string): Observable<FeeDetailModel> {
    return this.http.get<FeeDetailModel>(ClientApi.FEES_DETAIL.replace(/{fee_id}/gi, feeId));
  }
}
