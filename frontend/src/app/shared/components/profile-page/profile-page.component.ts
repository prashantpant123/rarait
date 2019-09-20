import {
  Component,
  Input
} from '@angular/core';
import {ReadOnlyModel} from '../read-only/read-only.model';
import {BreadcrumbModel} from '../breadcrumb/breadcrumb.model';
import {routerTransition} from '../../../router.animations';

@Component({
  selector: 'profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss'],
  animations: [routerTransition()]
})
export class ProfilePageComponent {

  @Input() route: BreadcrumbModel[];
  @Input() title: string;
  // @Input() website: string;
  @Input() schoolTitle: string;
  @Input() logo: string;
  @Input() contents: ReadOnlyModel[] = [];
  @Input() image_path: string;
  @Input() address:string;
  @Input() landline:string;






}
