import {ActivatedRoute} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {DatePipe} from '@angular/common';

import {routerTransition} from '../../../router.animations';
import {ReadOnlyModel} from '../../../shared/components/read-only/read-only.model';

import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../shared/constants/client-url';
import {StudentService} from '../core/service/student.service';
import {StudentDetailModel} from '../core/model/student/student-detail.model';
import {HeaderService} from "../core/service/header.service";
import {PdfService} from "../pdf/pdf.service";

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.scss'],
  animations: [routerTransition()]
})
export class StudentComponent implements OnInit {

  title: string;
  first_name: string;
  last_name: string;
  schoolTitle: string;
  logoPath: string;
  filename: string;
  address: string;
  landline: string;
  contents: ReadOnlyModel[] = [];
  imagePath: string;
  route: BreadcrumbModel[] = [];
  studentId = '';
  instituteId = '';

  studentDetails: StudentDetailModel;

  constructor(private activatedRoute: ActivatedRoute,
              private datePipe: DatePipe,
              private studentService: StudentService,
              private pdfService:PdfService,
              private headerService: HeaderService
  ) {
    this.studentId = this.activatedRoute.snapshot.paramMap.get('student_id');

    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Student', ClientUrl.STUDENT),
      new BreadcrumbModel('Detail', ClientUrl.STUDENT + '/' + this.studentId + '/detail')
    );
  }

  ngOnInit() {
    this.studentId = this.activatedRoute.snapshot.paramMap.get('student_id');

    this.studentService.getStudentDetail(this.studentId)
      .subscribe((data: StudentDetailModel) => {
        console.log('getting student details')
        this.populateInfo(data);
        this.studentDetails = data;
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
      this.schoolTitle=this.headerService.getSchoolBasicInfo().name;
      this.logoPath=this.headerService.getSchoolBasicInfo().logo;
      this.address=this.headerService.getSchoolBasicInfo().address;
      this.landline=this.headerService.getSchoolBasicInfo().landline;

    console.log('name:' +this.schoolTitle);
  }

  populateInfo(model: StudentDetailModel) {
    this.title = model.first_name + ' ' + model.last_name;
    this.imagePath = model.picture;
    this.contents.push(
      new ReadOnlyModel('Class', model.level),
      new ReadOnlyModel('Roll Number', model.roll_number),
      new ReadOnlyModel('Registration No.', model.registration_id),
      new ReadOnlyModel('Enrolled Date(AD)', this.datePipe.transform(new Date(model.enrolled_date), 'yyyy-MM-dd')),
      new ReadOnlyModel('Gender', model.gender),
      new ReadOnlyModel('Date Of Birth(BS)', model.date_of_birth_bs),
      new ReadOnlyModel('Date Of Birth(AD)', this.datePipe.transform(new Date(model.date_of_birth_ad), 'yyyy-MM-dd')),
      new ReadOnlyModel('Address', model.address),
      new ReadOnlyModel('Nationality', model.nationality));
    // new ReadOnlyModel('School Name', school_model.name);
    // new ReadOnlyModel('School Address', school_model.address);
    if (model.blood_group !== '') {
      this.contents.push(new ReadOnlyModel('Blood Group', model.blood_group));
    }
    this.contents.push(new ReadOnlyModel('Guardian Contact No.', model.guardian_contact),
      new ReadOnlyModel('Father\'s Name', model.father_name),
      new ReadOnlyModel('Father\'s Occupation', model.father_occupation),
      new ReadOnlyModel('Father\'s Contact No.', model.father_contact),
      new ReadOnlyModel('Mother\'s Name', model.mother_name),
      new ReadOnlyModel('Mother\'s Occupation', model.mother_occupation),
      new ReadOnlyModel('Mother\'s Contact No.', model.mother_contact),
      new ReadOnlyModel('Guardian Name', model.guardian_name),
      new ReadOnlyModel('Guardian Contact No.', model.guardian_contact)
    );
    if (model.transport !== '') {
      this.contents.push(new ReadOnlyModel('Transport', model.transport));
    }
  }
  generatePdf(){
    console.log("pdf button clicked");
    this.filename=this.title+ '/'+this.studentId;
    this.pdfService
      .downloadStudentPdf(this.studentId,this.filename);
  }
}
