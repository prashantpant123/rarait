import {Component, Input} from '@angular/core';
import {ReadOnlyModel} from './read-only.model';

@Component({
  selector: 'read-only',
  templateUrl: './read-only.component.html'
})
export class ReadOnlyComponent {

  @Input() title: string;
  @Input() contents: ReadOnlyModel[] = [];
}
