import {Component, Input} from '@angular/core';

@Component({
  selector: 'button-excel-card',
  template: '<a routerLink="{{routeLink}}" class="btn btn-primary btn-sm float-right">\n' +
  '            <i class="fa fa-file-excel-o mr-1"></i>Download on excel</a>'
})
export class ButtonExcelCardComponent {

  @Input() routeLink: string;

}
