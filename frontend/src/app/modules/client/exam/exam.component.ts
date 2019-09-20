import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

import {routerTransition} from '../../../router.animations';
import {ReadOnlyModel} from '../../../shared/components/read-only/read-only.model';

import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../shared/constants/client-url';
import {ExamService} from '../core/service/exam/exam.service';
import {ExamDetailModel} from '../core/model/exam/exam-detail.model';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  animations: [routerTransition()]
})
export class ExamComponent implements OnInit {

  title: string;
  contents: ReadOnlyModel[] = [];
  route: BreadcrumbModel[] = [];
  examId = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private datePipe: DatePipe,
    private examService: ExamService
  ) {
    this.examId = this.activatedRoute.snapshot.paramMap.get('exam_id');

    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Exam', ClientUrl.EXAM),
      new BreadcrumbModel('Detail', ClientUrl.EXAM + '/' + this.examId + '/detail')
    );
  }

  ngOnInit() {
    this.examId = this.activatedRoute.snapshot.paramMap.get('exam_id');

    this.examService.getExamDetail(this.examId)
      .subscribe((data: ExamDetailModel) => {
        this.populateInfo(data);
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
  }

  populateInfo(examDetail: ExamDetailModel) {
    this.title = examDetail.name;
    this.contents.push(
      new ReadOnlyModel('Academic Session', examDetail.academic_session),
      new ReadOnlyModel('Start Date / End Date (AD)',
        this.datePipe.transform(new Date(examDetail.start_date), 'yyyy-MM-dd') + ' to ' +
        this.datePipe.transform(new Date(examDetail.end_date), 'yyyy-MM-dd')),
      new ReadOnlyModel('Start Date / End Date(BS)', examDetail.start_date_bs + ' to ' + examDetail.end_date_bs),
      new ReadOnlyModel('Weightage % for final', examDetail.weightage + '')
    );
  }

}
