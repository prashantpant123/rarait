import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';

import {routerTransition} from '../../../router.animations';
import {ReadOnlyModel} from '../../../shared/components/read-only/read-only.model';
import {AdminURL} from '../../../shared/constants/admin-url';
import {AdminSchoolService} from '../core/service/admin-school.service';
import {AdminSchoolDetailModel} from '../core/model/admin-school-detail.model';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';

@Component({
  selector: 'app-admin-school',
  templateUrl: './admin-school.component.html',
  animations: [routerTransition()]
})
export class AdminSchoolComponent implements OnInit {

  title: string;
  contents: ReadOnlyModel[] = [];
  imagePath: string;
  route: BreadcrumbModel[] = [];
  instituteId = '';

  constructor(private activatedRoute: ActivatedRoute,
              private adminSchoolService: AdminSchoolService) {
    this.instituteId = this.activatedRoute.snapshot.paramMap.get('institute_id');
    this.route.push(
      new BreadcrumbModel('Dashboard', AdminURL.DASHBOARD),
      new BreadcrumbModel('School List', AdminURL.SCHOOL),
      new BreadcrumbModel('Detail', AdminURL.SCHOOL + '/' + this.instituteId + '/detail')
    );
  }

  ngOnInit() {
    this.instituteId = this.activatedRoute.snapshot.paramMap.get('institute_id');

    this.adminSchoolService.getSchoolDetail(this.instituteId)
      .subscribe((data: AdminSchoolDetailModel) => {
        this.populateInfo(data);
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
  }

  populateInfo(schoolDetail: AdminSchoolDetailModel) {
    this.title = schoolDetail.name;
    this.imagePath = schoolDetail.logo_path;
    this.contents.push(
      new ReadOnlyModel('Principal', schoolDetail.principal),
      new ReadOnlyModel('Address', schoolDetail.address),
      new ReadOnlyModel('Registration Code', schoolDetail.registration_prefix),
      new ReadOnlyModel('Admin Login', schoolDetail.user),
    );
    if (schoolDetail.landline !== '') {
      this.contents.push(new ReadOnlyModel('Phone No.', schoolDetail.landline));
    }
    if (schoolDetail.website !== '') {
      this.contents.push(new ReadOnlyModel('Website', schoolDetail.website));
    }
    if (schoolDetail.contact !== null) {
      if (schoolDetail.contact.full_name !== '') {
        this.contents.push(new ReadOnlyModel('Contact Name', schoolDetail.contact.full_name));
      }
      if (schoolDetail.contact.designation !== '') {
        this.contents.push(new ReadOnlyModel('Contact Designation', schoolDetail.contact.designation));
      }
      if (schoolDetail.contact.mobile_number !== '') {
        this.contents.push(new ReadOnlyModel('Contact Mobile No.', schoolDetail.contact.mobile_number));
      }
      if (schoolDetail.contact.landline_number !== '') {
        this.contents.push(new ReadOnlyModel('Contact Phone No.', schoolDetail.contact.landline_number));
      }
      if (schoolDetail.contact.email_id !== '') {
        this.contents.push(new ReadOnlyModel('Contact Email ID', schoolDetail.contact.email_id));
      }
    }
  }

}
