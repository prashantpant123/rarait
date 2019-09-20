import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

import {routerTransition} from '../../../router.animations';
import {ReadOnlyModel} from '../../../shared/components/read-only/read-only.model';

import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../shared/constants/client-url';
import {SubjectService} from '../core/service/subject.service';
import {SubjectDetailModel} from '../core/model/subject/subject-detail.model';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  animations: [routerTransition()]
})
export class SubjectComponent implements OnInit {

  title: string;
  contents: ReadOnlyModel[] = [];
  route: BreadcrumbModel[] = [];
  subjectId = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private subjectService: SubjectService
  ) {
    this.subjectId = this.activatedRoute.snapshot.paramMap.get('subject_id');

    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Subject', ClientUrl.SUBJECT),
      new BreadcrumbModel('Detail', ClientUrl.SUBJECT + '/' + this.subjectId + '/detail')
    );
  }

  ngOnInit() {
    this.subjectId = this.activatedRoute.snapshot.paramMap.get('subject_id');

    this.subjectService.getSubjectDetail(this.subjectId)
      .subscribe((data: SubjectDetailModel) => {
        this.populateInfo(data);
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
  }

  populateInfo(subjectModel: SubjectDetailModel) {
    this.title = subjectModel.name;
    this.contents.push(
      new ReadOnlyModel('Subject Code', subjectModel.code),
      new ReadOnlyModel('Class', subjectModel.level_name),
      new ReadOnlyModel('Subject Description', subjectModel.description),
      new ReadOnlyModel('Full Mark', subjectModel.full_mark + ''),
      new ReadOnlyModel('Pass Mark', subjectModel.pass_mark + ''),
      new ReadOnlyModel('Optional', subjectModel.optional === true ? 'Yes' : 'No'),
      new ReadOnlyModel('Practical', subjectModel.practical === true ? 'Yes' : 'No'));
    if (subjectModel.practical) {
      this.contents.push(
        new ReadOnlyModel('Practical Full Mark', subjectModel.practical_full_mark + ''),
        new ReadOnlyModel('Practical Pass Mark', subjectModel.practical_pass_mark + ''));
    }
  }

}
