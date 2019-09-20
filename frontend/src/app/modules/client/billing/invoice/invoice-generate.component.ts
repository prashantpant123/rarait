import {Component, OnInit} from '@angular/core';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';

@Component({
  selector: 'client-invoice-generate',
  templateUrl: './invoice-generate.component.html'
})
export class InvoiceGenerateComponent implements OnInit {

  route: BreadcrumbModel[] = [];

  constructor() {
  }

  ngOnInit(): void {
  }
}
