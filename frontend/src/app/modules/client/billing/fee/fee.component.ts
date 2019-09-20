import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {DatePipe} from '@angular/common';

import {routerTransition} from '../../../../router.animations';
import {ReadOnlyModel} from '../../../../shared/components/read-only/read-only.model';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {FeeService} from '../../core/service/billing/fee.service';
import {FeeDetailModel} from '../../core/model/billing/fee/fee-detail.model';

@Component({
  selector: 'app-fee',
  templateUrl: 'fee.component.html',
  animations: [routerTransition()]
})
export class FeeComponent implements OnInit {

  title: string;
  contents: ReadOnlyModel[] = [];
  route: BreadcrumbModel[] = [];
  feeId = '';

  constructor(private activatedRoute: ActivatedRoute,
              private feeService: FeeService,
              private datePipe: DatePipe) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Fees', ClientUrl.FEE),
      new BreadcrumbModel('Detail', ClientUrl.FEE + '/' + this.feeId + '/detail')
    );
  }

  ngOnInit() {
    this.feeId = this.activatedRoute.snapshot.paramMap.get('fee_id');

    this.feeService.getFeeDetail(this.feeId)
      .subscribe((data: FeeDetailModel) => {
        this.populateInfo(data);
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
  }

  populateInfo(model: FeeDetailModel) {
    this.title = model.title;
    this.contents.push(
      new ReadOnlyModel('Created Date', this.datePipe.transform(model.created_date, 'dd-MM-yyyy HH:MM')),
      new ReadOnlyModel('Description', model.description),
      new ReadOnlyModel('Amount(NPR)', model.amount + ''),
      new ReadOnlyModel('Discount', model.discount ? 'Yes' : 'No'));
    if (model.discount) {
      this.contents.push(new ReadOnlyModel('Discount Value', model.discount_value + ''));
    }
    this.contents.push(new ReadOnlyModel('Taxable', model.taxable ? 'Yes' : 'No'));
    if (model.taxable) {
      this.contents.push(new ReadOnlyModel('Tax Value', model.tax_value + ''));
    }
  }

}
