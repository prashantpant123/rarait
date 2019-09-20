import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

import {routerTransition} from '../../../router.animations';
import {ReadOnlyModel} from '../../../shared/components/read-only/read-only.model';

import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {ClassService} from '../core/service/class.service';
import {ClassDetailModel} from '../core/model/class/class-detail.model';
import {ClientUrl} from '../../../shared/constants/client-url';

@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  animations: [routerTransition()]
})
export class ClassComponent implements OnInit {

  title: string;
  contents: ReadOnlyModel[] = [];
  route: BreadcrumbModel[] = [];
  classId = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private classService: ClassService
  ) {
    this.classId = this.activatedRoute.snapshot.paramMap.get('class_id');

    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Classes', ClientUrl.CLASS),
      new BreadcrumbModel('Detail', ClientUrl.CLASS + '/' + this.classId + '/detail')
    );
  }

  ngOnInit() {
    this.classId = this.activatedRoute.snapshot.paramMap.get('class_id');

    this.classService.getClassDetail(this.classId)
      .subscribe((data: ClassDetailModel) => {
        this.populateInfo(data);
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
  }

  populateInfo(classDetail: ClassDetailModel) {
    this.title = classDetail.name;
    this.contents.push(
      new ReadOnlyModel('Category', classDetail.category),
      new ReadOnlyModel('Name', classDetail.name),
      new ReadOnlyModel('Code', classDetail.code),
      // new ReadOnlyModel('Section', classDetail.code)
    );
  }

}
