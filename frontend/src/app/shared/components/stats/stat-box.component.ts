import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'stat-box',
  templateUrl: './stat-box.component.html'
})
export class StatBoxComponent implements OnInit {
  @Input() bgClass: string;
  @Input() icon: string;
  @Input() count: number;
  @Input() label: string;
  @Output() event: EventEmitter<any> = new EventEmitter();

  constructor() {
  }

  ngOnInit() {
  }
}
