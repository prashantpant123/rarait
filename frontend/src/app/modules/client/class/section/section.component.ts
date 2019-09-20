import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

import {routerTransition} from '../../../../router.animations';
import {ReadOnlyModel} from '../../../../shared/components/read-only/read-only.model';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {SectionService} from '../../core/service/section.service';
import {SectionDetailModel} from '../../core/model/class/section/section-detail.model';

@Component({
  selector: 'client-class-section',
  templateUrl: './section.component.html',
  animations: [routerTransition()]
})
export class SectionComponent implements OnInit {

  title: string;
  contents: ReadOnlyModel[] = [];
  route: BreadcrumbModel[] = [];
  sectionId = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private sectionService: SectionService
  ) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Section', ClientUrl.SECTION),
      new BreadcrumbModel('Detail', ClientUrl.SECTION + '/' + this.sectionId + '/detail')
    );
  }

  ngOnInit() {
    this.sectionId = this.activatedRoute.snapshot.paramMap.get('section_id');

    this.sectionService.getSessionDetail(this.sectionId)
      .subscribe((data: SectionDetailModel) => {
        this.populateInfo(data);
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
  }

  populateInfo(model: SectionDetailModel) {
    this.title = model.class_name + '-' + model.name;
    this.contents.push(
      new ReadOnlyModel('Status', model.status),
    );
  }

}
