import {
  Component,
  Input
} from '@angular/core';
import {ReadOnlyModel} from '../read-only/read-only.model';
import {routerTransition} from '../../../router.animations';

@Component({
  selector: 'profile-print',
  templateUrl: './profile-print.component.html',
  styleUrls: ['./profile-print.component.scss'],
  animations: [routerTransition()]
})
export class ProfilePrintComponent {
  @Input() title: string;
  // @Input() website: string;
  @Input() schoolTitle: string;
  @Input() logo: string;
  @Input() contents: ReadOnlyModel[] = [];
  @Input() image_path: string;
}
