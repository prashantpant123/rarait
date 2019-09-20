import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {DatePipe} from '@angular/common';

import {routerTransition} from '../../../../router.animations';

import {ReadOnlyModel} from '../../../../shared/components/read-only/read-only.model';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../../shared/constants/client-url';

@Component({
  selector: 'user-profile',
  templateUrl: './user-profile.component.html',
  animations: [routerTransition()]
})
export class UserProfileComponent implements OnInit {

  route: BreadcrumbModel[] = [];
  userId = '';
  title$: string;
  contents$: ReadOnlyModel[] = [];

  constructor(
    private activatedRoute: ActivatedRoute,
    private datePipe: DatePipe
  ) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('User Profile List', ClientUrl.STUDENT)
    );
  }

  ngOnInit() {
    this.userId = this.activatedRoute.snapshot.paramMap.get('user_id');

  }

}
