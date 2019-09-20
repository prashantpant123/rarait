import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {DatePipe} from '@angular/common';

import {routerTransition} from '../../../router.animations';
import {ReadOnlyModel} from '../../../shared/components/read-only/read-only.model';

import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../shared/constants/client-url';
import {StaffService} from '../core/service/staff.service';
import {StaffDetailModel} from '../core/model/staff/staff-detail.model';
import {PdfService} from "../pdf/pdf.service";
import {HeaderService} from "../core/service/header.service";

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.scss'],
  animations: [routerTransition()]
})
export class StaffComponent implements OnInit {

  title: string;
  schoolTitle: string;
  logoPath: string;
  address:string;
  landline:string;
  contents: ReadOnlyModel[] = [];
  imagePath: string;
  filename:string
  route: BreadcrumbModel[] = [];
  staffId = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private datePipe: DatePipe,
    private staffService: StaffService,
    private pdfService:PdfService,
    private headerService:HeaderService

  ) {
    this.staffId = this.activatedRoute.snapshot.paramMap.get('staff_id');

    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Staff', ClientUrl.STAFF),
      new BreadcrumbModel('Detail', ClientUrl.STAFF + '/' + this.staffId + '/detail')
    );
  }

  ngOnInit() {
    this.staffId = this.activatedRoute.snapshot.paramMap.get('staff_id');

    this.staffService.getStaffDetail(this.staffId)
      .subscribe((data: StaffDetailModel) => {
        this.populateInfo(data);
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
    this.schoolTitle=this.headerService.getSchoolBasicInfo().name;
    this.logoPath=this.headerService.getSchoolBasicInfo().logo;
    this.address=this.headerService.getSchoolBasicInfo().address;
    this.landline=this.headerService.getSchoolBasicInfo().landline;

  }

    populateInfo(staffDetail: StaffDetailModel) {
    this.title = staffDetail.first_name + ' ' + staffDetail.last_name;
    this.imagePath = staffDetail.picture;
    this.contents.push(
      new ReadOnlyModel('Staff ID', staffDetail.employee_id),
      new ReadOnlyModel('Gender', staffDetail.gender),
      new ReadOnlyModel('Date Of Birth(AD)', this.datePipe.transform(new Date(staffDetail.date_of_birth), 'yyyy-MM-dd')),
      new ReadOnlyModel('Date Of Birth(BS)', staffDetail.dob_bs),
      new ReadOnlyModel('Blood Group', staffDetail.blood_group),
      new ReadOnlyModel('Marital Status', staffDetail.marital_status),
      new ReadOnlyModel('Nationality', staffDetail.nationality),
      new ReadOnlyModel('Current Address', staffDetail.current_address),
      new ReadOnlyModel('Permanent Address', staffDetail.permanent_address),
      new ReadOnlyModel('Primary Contact', staffDetail.primary_contact),
      new ReadOnlyModel('Secondary Contact', staffDetail.secondary_contact),
      new ReadOnlyModel('Role', staffDetail.employee_type),
      new ReadOnlyModel('Designation', staffDetail.designation),
      new ReadOnlyModel('Joining Date', this.datePipe.transform(new Date(staffDetail.joining_date), 'yyyy-MM-dd')),
      new ReadOnlyModel('Qualification', staffDetail.qualification),
    );
  }

generatePdf(){
    console.log("pdf button clicked");
    this.filename=this.title;
      this.pdfService
      .downloadPdf(this.staffId,this.filename);
}


}
