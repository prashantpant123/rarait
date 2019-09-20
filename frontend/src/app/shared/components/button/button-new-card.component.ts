import {Component, Input} from '@angular/core';

@Component({
  selector: 'button-new-card',
  template: '<a routerLink="{{routeLink}}" class="btn btn-primary btn-sm float-right">\n' +
  '            <i class="fa fa-plus mr-1"></i>{{text}}</a>'
})
export class ButtonNewCardComponent {

  @Input() routeLink: string;
  @Input() text: string;

}
