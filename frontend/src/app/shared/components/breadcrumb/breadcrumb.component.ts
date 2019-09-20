import {Component, Input} from '@angular/core';
import {BreadcrumbModel} from './breadcrumb.model';

@Component({
  selector: 'breadcrumb',
  templateUrl: './breadcrumb.component.html'
})
export class BreadcrumbComponent {

  @Input() links: BreadcrumbModel[];

}
