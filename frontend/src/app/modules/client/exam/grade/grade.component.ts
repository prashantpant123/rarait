import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {DatePipe} from '@angular/common';

import {routerTransition} from '../../../../router.animations';
import {ReadOnlyModel} from '../../../../shared/components/read-only/read-only.model';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {GradeService} from '../../core/service/exam/grade.service';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {GradeDetailModel} from '../../core/model/exam/grade/grade-detail.model';

@Component({
  selector: 'app-grade',
  templateUrl: './grade.component.html',
  animations: [routerTransition()]
})
export class GradeComponent implements OnInit {

  title: string;
  contents: ReadOnlyModel[] = [];
  route: BreadcrumbModel[] = [];
  gradeId = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private datePipe: DatePipe,
    private gradeService: GradeService
  ) {
    this.gradeId = this.activatedRoute.snapshot.paramMap.get('exam_id');

    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Grade', ClientUrl.GRADE),
      new BreadcrumbModel('Detail', ClientUrl.GRADE + '/' + this.gradeId + '/detail')
    );
  }

  ngOnInit() {
    this.gradeId = this.activatedRoute.snapshot.paramMap.get('grade_id');

    this.gradeService.getGradeDetail(this.gradeId)
      .subscribe((data: GradeDetailModel) => {
        this.populateInfo(data);
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
  }

  populateInfo(model: GradeDetailModel) {
    this.title = 'Grade Detail';
    this.contents.push(
      new ReadOnlyModel('Grade Value', model.value),
      new ReadOnlyModel('Low Mark', model.low_mark + ''),
      new ReadOnlyModel('High Mark', model.high_mark + ''),
      new ReadOnlyModel('Created On', this.datePipe.transform(new Date(model.created_date), 'dd-MM-yyyy')),
      new ReadOnlyModel('Last Update On', this.datePipe.transform(new Date(model.last_modified_date), 'dd-MM-yyyy')),
    );
  }

}
