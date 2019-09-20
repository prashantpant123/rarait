import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';

import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {FeeDetailModel} from '../../core/model/billing/fee/fee-detail.model';
import {FeeService} from '../../core/service/billing/fee.service';
import {ReadOnlyModel} from '../../../../shared/components/read-only/read-only.model';
import {routerTransition} from '../../../../router.animations';

@Component({
  selector: 'client-invoice',
  templateUrl: './invoice.component.html',
  animations: [routerTransition()]
})
export class InvoiceComponent implements OnInit {

  title: string;
  contents: ReadOnlyModel[] = [];
  route: BreadcrumbModel[] = [];
  invoiceId = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private feeService: FeeService
  ) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Invoice', ClientUrl.INVOICE),
      new BreadcrumbModel('Detail', ClientUrl.INVOICE_DETAIL + '/' + this.invoiceId + '/detail')
    );
  }

  ngOnInit() {
    this.invoiceId = this.activatedRoute.snapshot.paramMap.get('invoice_id');

    this.feeService.getFeeDetail(this.invoiceId)
      .subscribe((data: FeeDetailModel) => {
        this.populateInfo(data);
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
  }

  populateInfo(model: FeeDetailModel) {
    this.title = model.title;
    this.contents.push(
      new ReadOnlyModel('Title', model.title),
      new ReadOnlyModel('Amount(NPR)', model.amount + ''),
      new ReadOnlyModel('Taxable', model.taxable ? 'Yes' : 'No'),
      new ReadOnlyModel('Tax Value', model.tax_value + ''),
      new ReadOnlyModel('Description', model.description)
    );
  }
}
