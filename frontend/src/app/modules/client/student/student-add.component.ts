import {AfterViewInit, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';

import {routerTransition} from '../../../router.animations';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../shared/constants/client-url';
import {ClassService} from '../core/service/class.service';
import {ConfirmationDialogSevice} from '../../../shared/components/confirmation-dailog/confirmation-dialog.sevice';
import {AddressService} from '../../../shared/services/address.service';
import {OccupationService} from '../../../shared/services/occupation.service';
import {AddressListModel} from '../../../shared/model/address-list.model';
import {OccupationListModel} from '../../../shared/model/occupation-list.model';
import {ClassDropdownModel} from '../core/model/class/class-dropdown.model';
import {DisplayListModel} from '../../../shared/components/confirmation-dailog/display-list.model';
import {StudentCreateModel} from '../core/model/student/student-create.model';
import {StudentService} from '../core/service/student.service';
import {TransportService} from '../core/service/transport.service';
import {TransportDropdownModel} from '../core/model/transport/transport-dropdown.model';
import {NzMessageService} from 'ng-zorro-antd';
import {DropdownModel} from '../../../shared/model/dropdown.model';
import {SectionService} from '../core/service/section.service';
import {HttpEventType, HttpResponse} from '@angular/common/http';
import {AdminURL} from '../../../shared/constants/admin-url';
import {InstituteFileUploadService} from '../core/service/institute-file-upload.service';
import {DocumentType} from '../../../shared/constants/document-type';
import {BloodGroupService} from '../../../shared/services/blood-group.service';
import {NationalityService} from '../../../shared/services/nationality.service';
import {HeaderService} from '../core/service/header.service';

@Component({
  selector: 'app-student-add',
  templateUrl: './student-add.component.html',
  animations: [routerTransition()]
})
export class StudentAddComponent implements OnInit, AfterViewInit {

  route: BreadcrumbModel[] = [];
  studentRegisterForm: FormGroup;
  submitted = false;

  occupations: OccupationListModel[] = [];
  clazzList: ClassDropdownModel[] = [];
  sectionList: DropdownModel[] = [];
  transportList: TransportDropdownModel[] = [];
  bloodGroupList: DropdownModel[] = [];
  nationalityList: DropdownModel[] = [];

  occupationSelectedId: number;
  occupationSelectedName: string;
  momOccupationSelectedId: number;
  momOccupationSelectedName: string;
  classSelectedId: number;
  classSelectedName: string;
  sectionSelectId: number;
  sectionSelectName: string;
  busRouteSelectedId: number;
  busRouteSelectedName: string;
  nationalitySelectedId: number;
  nationalitySelectedName: string;
  bloodGroupSelectedId: number;
  bloodGroupSelectedName: string;

  dateOfBirth: Date;
  _gender: any;
  // _dateOfBirth: any;
  _size = 'large';
  montessori = true;
  reg_prefix: string;

  selectedFile: File = null;
  progress: { percentage: number } = {percentage: 0};

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private addressService: AddressService,
              private occupationService: OccupationService,
              private classService: ClassService,
              private sectionService: SectionService,
              private studentService: StudentService,
              private transportService: TransportService,
              private confirmDialogService: ConfirmationDialogSevice,
              private fileUploadService: InstituteFileUploadService,
              private bloodGroupService: BloodGroupService,
              private nationalityService: NationalityService,
              private headerService: HeaderService,
              private datePipe: DatePipe,
              private message: NzMessageService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Students', ClientUrl.STUDENT),
      new BreadcrumbModel('Create', ClientUrl.STUDENT + '/create')
    );
  }

  ngOnInit() {
    this.studentRegisterForm = this.formBuilder.group({
      studentFirstName: ['', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50),
      ]],
      studentLastName: ['', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50)
      ]],

      studentDOB: ['',
        Validators.required
      ],
      studentGender: [this._gender, Validators.required],
      studentRollNumber: ['',
        Validators.required
      ],

      studentFatherName: ['',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(30)
        ]
      ],
      studentFatherContact: [''],
      studentMotherName: ['',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(30)
        ]
      ],
      studentMotherContact: [''],
      studentGuardianName: [''],
      studentGuardianContact: [''],
      studentAddress: ['',
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(50)
        ]
      ],
      studentWeight: [''],
      studentHeight: [''],
      studentRegistrationNumber: ['', {
        validators: [Validators.required,
          Validators.minLength(3),
          Validators.maxLength(6)
        ]
      }],
      registrationPrefix: ['']
    });

    this.studentRegisterForm.get('studentFatherName').valueChanges.subscribe(value => {
      this.studentRegisterForm.get('studentGuardianName').patchValue(value);
    });

    this.studentRegisterForm.get('studentFatherContact').valueChanges.subscribe(value => {
      this.studentRegisterForm.get('studentGuardianContact').patchValue(value);
    });

    this.getOccupations();
    this.getClassList();
    this.getBusRouteList();
    this.getBloodGroup();
    this.getNationality();
  }

  ngAfterViewInit() {
    const data = this.headerService.getSchoolBasicInfo();
    this.reg_prefix = data.registration_no_prefix;
    this.studentRegisterForm.controls['registrationPrefix'].setValue(this.reg_prefix);
  }

  get f() {
    return this.studentRegisterForm.controls;
  }

  getOccupations() {
    this.occupationService.getAllOccupations()
      .subscribe(data => {
        this.occupations = data;
      });
  }

  getClassList() {
    this.classService.getClassDropdown()
      .subscribe(data => {
        this.clazzList = data;
      });
  }

  getBusRouteList() {
    this.transportService.getTransportDropdown()
      .subscribe(data => {
        this.transportList = data;
      });
  }

  getSectionList() {
    this.sectionService.getSectionDropdownForClass(this.classSelectedId)
      .subscribe(data => {
        this.sectionList = data;
      });
  }

  getBloodGroup() {
    this.bloodGroupService.getAllBloodGroup()
      .subscribe(data => {
        this.bloodGroupList = data;
      });
  }

  getNationality() {
    this.nationalityService.getAllNationality()
      .subscribe(data => {
        this.nationalityList = data;
      });
  }

  classSelectEvent(selectedId: any, selectedName: any) {
    if (selectedId !== '-1') {
      this.classSelectedId = selectedId;
      this.classSelectedName = selectedName;
      this.getSectionList();
    }
  }

  sectionSelectEvent(selectId: any, selectvalue: any) {
    if (selectId !== '-1') {
      this.sectionSelectId = selectId;
      this.sectionSelectName = selectvalue;
    }
  }

  occupationSelectEvent(selectedId: any, selectedName: any) {
    if (selectedId !== '-1') {
      this.occupationSelectedId = selectedId;
      this.occupationSelectedName = selectedName;
    }
  }

  momOccupationSelectEvent(selectedId: any, selectedName: any) {
    if (selectedId !== '-1') {
      this.momOccupationSelectedId = selectedId;
      this.momOccupationSelectedName = selectedName;
    }
  }

  busSelectEvent(selectedId: any, selectedName: any) {
    if (selectedId === '-1') {
      this.busRouteSelectedId = 0;
      this.busRouteSelectedName = '';
    } else {
      this.busRouteSelectedId = selectedId;
      this.busRouteSelectedName = selectedName;
    }
  }

  bloodGroupSelectEvent(selectedId: any, selectedName: any) {
    if (selectedId !== '-1') {
      this.bloodGroupSelectedId = selectedId;
      this.bloodGroupSelectedName = selectedName;
    }
  }

  nationalitySelectEvent(selectedId: any, selectedName: any) {
    if (selectedId !== '-1') {
      this.nationalitySelectedId = selectedId;
      this.nationalitySelectedName = selectedName;
    }
  }

  onDOBSelectEvent(result: Date): void {
    this.dateOfBirth = result;
  }

  fileSelected(event: any) {
    this.selectedFile = <File>event.target.files[0];
  }

  registrationNumberCheck() {
    const value = this.studentRegisterForm.value.studentRegistrationNumber;
    if (value.length > 2) {
      this.studentService.checkRegistrationNumber(value)
        .subscribe(data => {
          if (!data || (data.exist === true)) {
            this.studentRegisterForm.controls['studentRegistrationNumber'].setErrors({'exist': true});
          } else {
            this.studentRegisterForm.controls['studentRegistrationNumber'].setErrors(null);
          }
        });
    }
  }

  onSubmit() {
    this.submitted = true;
    if (this.studentRegisterForm.invalid) {
      return;
    }
    this.confirmDialog();
  }

  public confirmDialog() {
    const displayList: DisplayListModel[] = [];
    displayList.push(
      new DisplayListModel('Full Name', this.studentRegisterForm.value.studentFirstName + ' '
        + this.studentRegisterForm.value.studentLastName),
      new DisplayListModel('DOB(AD)', this.datePipe.transform(this.dateOfBirth, 'mediumDate')),
      new DisplayListModel('Gender', this.studentRegisterForm.value.studentGender),
      new DisplayListModel('Class', this.classSelectedName + (this.sectionSelectName === '' ? '' : '-' + this.sectionSelectName)),
      new DisplayListModel('Roll Number', this.studentRegisterForm.value.studentRollNumber),
      new DisplayListModel('Registration Number', this.reg_prefix + this.studentRegisterForm.value.studentRegistrationNumber),
      new DisplayListModel('Father\'s Name', this.studentRegisterForm.value.studentFatherName),
      new DisplayListModel('Father\'s Contact No.', this.studentRegisterForm.value.studentFatherContact));

    if (this.occupationSelectedId > 0) {
      displayList.push(new DisplayListModel('Father\'s Occupation', this.occupationSelectedName));
    }

    displayList.push(new DisplayListModel('Mother\'s Name', this.studentRegisterForm.value.studentMotherName),
      new DisplayListModel('Mother\'s Contact No.', this.studentRegisterForm.value.studentMotherContact));
    if (this.momOccupationSelectedId > 0) {
      displayList.push(new DisplayListModel('Mother\'s Occupation', this.momOccupationSelectedName));
    }

    displayList.push(new DisplayListModel('Nationality', this.nationalitySelectedName));
    if (this.bloodGroupSelectedName !== '') {
      displayList.push(new DisplayListModel('Blood Group', this.bloodGroupSelectedName));
    }

    if (this.studentRegisterForm.value.studentGuardianName !== '') {
      displayList.push(new DisplayListModel('Guardian Name', this.studentRegisterForm.value.studentGuardianName));
    }
    if (this.studentRegisterForm.value.studentGuardianContact !== '') {
      displayList.push(new DisplayListModel('Guardian Contact', this.studentRegisterForm.value.studentGuardianContact));
    }
    displayList.push(new DisplayListModel('Address', this.studentRegisterForm.value.studentAddress),
      new DisplayListModel('Transport Route', this.busRouteSelectedId === 0 ? 'N/A' : this.busRouteSelectedName)
    );
    if (this.studentRegisterForm.value.studentHeight !== '') {
      displayList.push(new DisplayListModel('Height', this.studentRegisterForm.value.studentHeight),
        new DisplayListModel('Weight', this.studentRegisterForm.value.studentWeight));
    }


    this.confirmDialogService.confirm('Confirm', 'Cancel', displayList)
      .then((confirmed) => {
        this.processRequest();
      })
      .catch(() => console.log('User dismissed the dialog'));
  }

  private processRequest() {
    const model = new StudentCreateModel();
    model.first_name = this.studentRegisterForm.value.studentFirstName;
    model.last_name = this.studentRegisterForm.value.studentLastName;
    model.gender = this.studentRegisterForm.value.studentGender === 'Male' ? 1 : 2;
    model.class_id = this.classSelectedId;
    model.section_id = this.sectionSelectId;
    model.roll_number = this.studentRegisterForm.value.studentRollNumber;
    model.registration_number = this.studentRegisterForm.value.studentRegistrationNumber;
    model.date_of_birth = this.dateOfBirth;
    model.father_name = this.studentRegisterForm.value.studentFatherName;
    model.mother_name = this.studentRegisterForm.value.studentMotherName;
    model.father_occupation_id = this.occupationSelectedId;
    model.mother_occupation_id = this.momOccupationSelectedId;
    model.father_contact = this.studentRegisterForm.value.studentFatherContact;
    model.mother_contact = this.studentRegisterForm.value.studentMotherContact;
    model.guardian_name = this.studentRegisterForm.value.studentGuardianName;
    model.guardian_contact = this.studentRegisterForm.value.studentGuardianContact;
    model.address = this.studentRegisterForm.value.studentAddress;
    model.bus_route_id = this.busRouteSelectedId;
    model.weight = this.studentRegisterForm.value.studentWeight;
    model.height = this.studentRegisterForm.value.studentHeight;
    model.blood_group = this.bloodGroupSelectedId;
    model.nationality_id = this.nationalitySelectedId;

    this.studentService.createNewStudent(model)
      .subscribe(data => {
        this.uploadFile(data.id);
      }, error => {
        console.log('Error ' + error);
      });
    this.submitted = false;
  }

  private uploadFile(studentId: number) {
    this.fileUploadService.uploadFile(this.selectedFile, studentId, DocumentType.STUDENT_PICTURE)
      .subscribe(event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress.percentage = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.router.navigateByUrl(ClientUrl.STUDENT);
          this.message.success('New student \'<b>' + this.studentRegisterForm.value.studentFirstName +
            '</b>\' detail has been registered successfully', {nzDuration: 5000});
          this.onCancel();
        }
      }, error => {
        console.log('Document upload error ' + error);
        this.message.error('Sorry, document couldn\'t be uploaded at the moment. Please try again later.', {nzDuration: 5000});
      });
  }

  onCancel() {
    this.studentRegisterForm.reset();
  }
}
