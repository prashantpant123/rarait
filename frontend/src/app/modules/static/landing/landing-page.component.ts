import {Component, OnInit} from '@angular/core';
import {routerTransition} from '../../../router.animations';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./css/landing-page.component.css', './css/all.min.css', './css/magnific-popup.css'],
  animations: [routerTransition()]
})
export class LandingPageComponent implements OnInit {
  constructor(private translate: TranslateService) {
  }

  ngOnInit() {
  }
}
