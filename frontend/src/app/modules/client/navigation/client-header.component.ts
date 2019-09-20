import {Component, OnInit} from '@angular/core';
import {Router, NavigationEnd} from '@angular/router';
import {TranslateService} from '@ngx-translate/core';

import {AuthenticationService} from '../../../shared/services/authentication.service';
import {HeaderService} from '../core/service/header.service';

@Component({
  selector: 'app-client-header',
  templateUrl: './client-header.component.html',
  styleUrls: ['./client-header.component.scss']
})
export class ClientHeaderComponent implements OnInit {
  public pushRightClass: string;
  name: string;
  username: string;
  logo: string;

  constructor(private translate: TranslateService,
              public router: Router,
              private authService: AuthenticationService,
              private headerService: HeaderService) {

    this.translate.addLangs(['en', 'np']);
    this.translate.setDefaultLang('en');
    const browserLang = this.translate.getBrowserLang();
    this.translate.use(browserLang.match(/en|np/) ? browserLang : 'en');

    this.router.events.subscribe(val => {
      if (
        val instanceof NavigationEnd &&
        window.innerWidth <= 992 &&
        this.isToggled()
      ) {
        this.toggleSidebar();
      }
    });
  }

  ngOnInit() {
    this.pushRightClass = 'push-right';
    this.getBasicInfo();
  }

  isToggled(): boolean {
    const dom: Element = document.querySelector('body');
    return dom.classList.contains(this.pushRightClass);
  }

  toggleSidebar() {
    const dom: any = document.querySelector('body');
    dom.classList.toggle(this.pushRightClass);
  }

  onLoggedout() {
    this.authService.logout();
  }

  changeLang(language: string) {
    this.translate.use(language);
  }

  getBasicInfo() {
    this.headerService.schoolBasicInfo()
      .subscribe(data => {
        this.name = data.name;
        this.username = data.username;
        this.logo = data.logo;
      });
  }
}
