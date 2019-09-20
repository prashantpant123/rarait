import {Component, OnInit} from '@angular/core';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';

@Component({
  selector: 'client-invoice-list',
  templateUrl: './invoice-list.component.html'
})
export class InvoiceListComponent implements OnInit {

  route: BreadcrumbModel[] = [];

  constructor() {
  }

  ngOnInit(): void {
  }
}
