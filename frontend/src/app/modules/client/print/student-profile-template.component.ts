import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {PrintService} from "./print_service";
import {HttpErrorResponse} from "@angular/common/http";
import {StudentDetailModel} from "../core/model/student/student-detail.model";
import {BreadcrumbModel} from "../../../shared/components/breadcrumb/breadcrumb.model";
import {DatePipe} from "@angular/common";
import {StudentService} from "../core/service/student.service";
import {ClientUrl} from "../../../shared/constants/client-url";
import {ReadOnlyModel} from "../../../shared/components/read-only/read-only.model";


@Component({
  selector: 'app-student-profile-template',
  templateUrl: './student-profile-template.component.html',

})
export class StudentProfileTemplateComponent implements OnInit {
  studentDetails: StudentDetailModel;
  title: string;
  contents: ReadOnlyModel[] = [];
  imagePath: string;
  route: BreadcrumbModel[] = [];
  studentId = '';
  class: string;
  roll_no: string;
  enrolled_date: string;
  gender:string;
  nationality: string;
  dob_bs: string;
  dob_ad:string;
  address: string;
  father_name: string;
  father_occupation:string;
  father_contact:string;
  mother_name:string;
  mother_occupation:string;
  mother_contact:string;
  guardian_name:string;
  guardian_contact:string;


  constructor(
    private activatedRoute: ActivatedRoute,
    private datePipe: DatePipe,
    private studentService: StudentService,
    route: ActivatedRoute,
              private printService: PrintService) {
    this.studentDetails = route.snapshot.params['studentDetails']
      .split(',');
    this.studentId = this.activatedRoute.snapshot.paramMap.get('student_id');

    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Student', ClientUrl.STUDENT),
      new BreadcrumbModel('Detail', ClientUrl.STUDENT + '/' + this.studentId + '/detail')
    );
  }


  ngOnInit() {
    console.log('After Init call');
    this.studentId = this.activatedRoute.snapshot.paramMap.get('student_id');

    this.studentService.getStudentDetail(this.studentId)
      .subscribe((data: StudentDetailModel) => {
        this.printData(data);
        this.studentDetails=data;
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);

      });
    console.log('before dataReady call');
    this.printService.onDataReady();

  }
  printData(model: StudentDetailModel) {
    this.title = model.first_name + '' + model.level;
    this.imagePath = model.picture;
    this.class = model.level;
    this.roll_no = model.roll_number;
    this.enrolled_date = model.enrolled_date;
    this.dob_bs = model.date_of_birth_bs;
    this.dob_ad = model.date_of_birth_ad;
    this.nationality=model.nationality;
    this.address = model.address, this.father_name = model.father_name;
    this.father_occupation = model.father_occupation;
    this.father_contact = model.father_contact;
    this.mother_name = model.mother_name;
    this.mother_occupation = model.mother_occupation;
    this.mother_contact = model.mother_contact;
    this.guardian_name = model.guardian_name;
    this.guardian_contact = model.guardian_contact;
  }

}
