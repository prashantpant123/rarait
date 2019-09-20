// import {Component, OnInit} from "@angular/core";
// import {ActivatedRoute} from "@angular/router";
// import {HttpErrorResponse} from "@angular/common/http";
// import {BreadcrumbModel} from "../../../shared/components/breadcrumb/breadcrumb.model";
// import {DatePipe} from "@angular/common";
// import {StaffService} from "../core/service/staff.service";
// import {ClientUrl} from "../../../shared/constants/client-url";
// import {ReadOnlyModel} from "../../../shared/components/read-only/read-only.model";
// import {StaffDetailModel} from "../core/model/staff/staff-detail.model";
// import {PdfService} from "./pdf.service";
// import {StudentDetailModel} from "../core/model/student/student-detail.model";
//
// @Component({
//   selector: 'app-staff-profile-template',
//   templateUrl: './staff-profile-template.component.html',
//
// })
//
// export class StaffProfileTemplateComponent implements OnInit {
//   staffDetails: StaffDetailModel;
//   title: string;
//   contents: ReadOnlyModel[] = [];
//   imagePath: string;
//   route: BreadcrumbModel[] = [];
//   staffId = '';
//   first_name: string;
//   last_name: string;
//   date_of_birth: string;
//   dob_bs: string;
//   employee_type: string;
//   gender: string;
//   current_address: string;
//   permanent_address: string;
//   joining_date: string;
//   primary_contact: string;
//   secondary_contact: string;
//   designation: string;
//   qualification: string;
//   constructor(
//     private activatedRoute: ActivatedRoute,
//     private datePipe: DatePipe,
//     private staffService: StaffService,
//     private studentDetail:StudentDetailModel,
//     route: ActivatedRoute,
//     private pdfService: PdfService) {
//     this.staffDetails = route.snapshot.params['staffDetails']
//       .split(',');
//     this.staffId = this.activatedRoute.snapshot.paramMap.get('staff_id');
//
//     this.route.push(
//       new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
//       new BreadcrumbModel('Staff', ClientUrl.STAFF),
//       new BreadcrumbModel('Detail', ClientUrl.STAFF + '/' + this.staffId + '/detail')
//     );
//   }
//
//   ngOnInit() {
//     console.log('After Init call');
//     this.staffId = this.activatedRoute.snapshot.paramMap.get('staff_id');
//
//     this.staffService.getStaffDetail(this.staffId)
//       .subscribe((data: StaffDetailModel) => {
//         this.printData(data);
//         this.staffDetails=data;
//       }, (error: HttpErrorResponse) => {
//         console.log('error: ' + error);
//       });
//     console.log('before dataReady call');
//     this.pdfService.downloadPdf(this.staffId);
//     console.log("staff-profile-template-component.ts");
//   }
//
//   printData(model: StaffDetailModel) {
//     this.title = model.first_name + '' ;
//     this.imagePath = model.picture;
//     this.employee_type =model.employee_type;
//     this.dob_bs = model.dob_bs
//     this.date_of_birth = model.date_of_birth;
//     this.gender=model.gender;
//     this.current_address=model.current_address;
//     this.permanent_address=model.permanent_address;
//     this.joining_date=model.joining_date;
//     this.primary_contact=model.primary_contact;
//     this.secondary_contact=model.secondary_contact;
//     this.designation=model.designation;
//     this.qualification=model.qualification;
//   }
// }
