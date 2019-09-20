import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';
import {DatePipe} from '@angular/common';

import {routerTransition} from '../../../../router.animations';
import {AdminURL} from '../../../../shared/constants/admin-url';

import {ReadOnlyModel} from '../../../../shared/components/read-only/read-only.model';
import {AdminUserService} from '../../core/service/admin-user.service';
import {UserInfoModel} from '../../core/model/user-info.model';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';

@Component({
  selector: 'app-admin-user-profile',
  templateUrl: './admin-user-profile.component.html',
  animations: [routerTransition()]
})
export class AdminUserProfileComponent implements OnInit {

  title: string;
  contents: ReadOnlyModel[] = [];
  route: BreadcrumbModel[] = [];
  userId = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private adminUserService: AdminUserService,
    private datePipe: DatePipe
  ) {
    this.route.push(
      new BreadcrumbModel('Dashboard', AdminURL.DASHBOARD),
      new BreadcrumbModel('Profile', AdminURL.PROFILE_USER)
    );
  }

  ngOnInit() {
    this.userId = this.activatedRoute.snapshot.paramMap.get('user_id');

    this.adminUserService.getAdminUserDetail(this.userId)
      .subscribe((data: UserInfoModel) => {
        this.populateInfo(data);
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
  }

  populateInfo(userInfo: UserInfoModel) {
    this.title = 'Admin User Info';
    this.contents.push(
      new ReadOnlyModel('Username', userInfo.username),
      new ReadOnlyModel('Roles', userInfo.role),
      new ReadOnlyModel('Status', userInfo.status),
      new ReadOnlyModel('Last Login',
        this.datePipe.transform(new Date(userInfo.last_login_date), 'dd-MM-yyyy HH:MM'))
    );
  }
}
