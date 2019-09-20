import {Component, OnInit} from '@angular/core';
import {routerTransition} from '../../../router.animations';
import {AdminURL} from '../../../shared/constants/admin-url';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';

@Component({
  selector: 'app-admin-setting',
  templateUrl: './admin-setting.component.html',
  animations: [routerTransition()]
})
export class AdminSettingComponent implements OnInit {
  route: BreadcrumbModel[] = [];

  constructor() {
    this.route.push(
      new BreadcrumbModel('Dashboard', AdminURL.DASHBOARD),
      new BreadcrumbModel('Setting', AdminURL.PROFILE_USER)
    );
  }

  ngOnInit() {
  }
}
