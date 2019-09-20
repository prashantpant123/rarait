import {Component, Input} from '@angular/core';
import {BreadcrumbModel} from '../breadcrumb/breadcrumb.model';
import {routerTransition} from '../../../router.animations';

@Component({
  selector: 'detail-page',
  templateUrl: './detail-page.component.html',
  animations: [routerTransition()]
})
export class DetailPageComponent {

  @Input() csRoute: BreadcrumbModel[];
  @Input() csTitle: string;
  @Input() csContent: string;

}
