import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {DatePipe} from '@angular/common';

import {routerTransition} from '../../../router.animations';
import {ReadOnlyModel} from '../../../shared/components/read-only/read-only.model';

import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../shared/constants/client-url';
import {AcademicSessionService} from '../core/service/academic-session.service';
import {AcademicSessionDetailModel} from '../core/model/academic-session/academic-session-detail.model';

@Component({
  selector: 'app-academic-session',
  templateUrl: './academic-session.component.html',
  animations: [routerTransition()]
})
export class AcademicSessionComponent implements OnInit {

  title: string;
  contents: ReadOnlyModel[] = [];
  route: BreadcrumbModel[] = [];
  academicSessionId = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private academicSessionService: AcademicSessionService,
    private datePipe: DatePipe
  ) {
    this.academicSessionId = this.activatedRoute.snapshot.paramMap.get('session_id');

    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Academic Session', ClientUrl.ACADEMIC_SESSION),
      new BreadcrumbModel('Detail', ClientUrl.ACADEMIC_SESSION + '/' + this.academicSessionId + '/detail')
    );
  }

  ngOnInit() {
    this.academicSessionId = this.activatedRoute.snapshot.paramMap.get('session_id');

    this.academicSessionService.getSessionDetail(this.academicSessionId)
      .subscribe((data: AcademicSessionDetailModel) => {
        this.populateInfo(data);
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
  }

  populateInfo(sessionDetail: AcademicSessionDetailModel) {
    this.title = sessionDetail.name;

    this.contents.push(
      new ReadOnlyModel('Start Date - End Date (BS)', this.datePipe.transform(new Date(sessionDetail.start_date_bs), 'dd-MM-yyyy')
        + ' to ' + this.datePipe.transform(new Date(sessionDetail.end_date_bs), 'dd-MM-yyyy')),
      new ReadOnlyModel('Start Date - End Date (AD)', this.datePipe.transform(new Date(sessionDetail.start_date_ad), 'dd-MM-yyyy')
        + ' to ' + this.datePipe.transform(new Date(sessionDetail.end_date_ad), 'dd-MM-yyyy')),
      new ReadOnlyModel('Current Session', sessionDetail.current_session === true ? 'Yes' : 'No')
    );
  }

}
