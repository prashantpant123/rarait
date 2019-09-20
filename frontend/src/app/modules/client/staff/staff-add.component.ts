import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {HttpEventType, HttpResponse} from '@angular/common/http';
import {NzMessageService} from 'ng-zorro-antd';
import {DatePipe} from '@angular/common';

import {ClientUrl} from '../../../shared/constants/client-url';
import {routerTransition} from '../../../router.animations';
import {ConfirmationDialogSevice} from '../../../shared/components/confirmation-dailog/confirmation-dialog.sevice';
import {StaffService} from '../core/service/staff.service';
import {DisplayListModel} from '../../../shared/components/confirmation-dailog/display-list.model';
import {StaffCreateModel} from '../core/model/staff/staff-create.model';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {StaffDropdownModel} from '../core/model/staff/staff-dropdown.model';
import {InstituteFileUploadService} from '../core/service/institute-file-upload.service';
import {DocumentType} from '../../../shared/constants/document-type';
import {BloodGroupService} from '../../../shared/services/blood-group.service';
import {NationalityService} from '../../../shared/services/nationality.service';
import {DropdownModel} from '../../../shared/model/dropdown.model';
import {MaritalStatusService} from '../../../shared/services/marital-status.service';

@Component({
  selector: 'app-staff-add',
  templateUrl: './staff-add.component.html',
  animations: [routerTransition()]
})
export class StaffAddComponent implements OnInit {

  staffCreateForm: FormGroup;
  submitted = false;
  route: BreadcrumbModel[] = [];

  staffTypes: StaffDropdownModel[];
  bloodGroupList: DropdownModel[] = [];
  nationalityList: DropdownModel[] = [];
  maritalStatusList: DropdownModel[] = [];

  typeSelectId: number;
  typeSelectName: string;
  nationalitySelectedId: number;
  nationalitySelectedName: string;
  bloodGroupSelectedId: number;
  bloodGroupSelectedName: string;
  maritalStatusSelectedId: number;
  maritalStatusSelectedName: string;

  _joiningDate: any;
  _staffDOB: any;
  _gender: any;
  _size = 'large';

  selectedFile: File = null;
  progress: { percentage: number } = {percentage: 0};

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private staffService: StaffService,
              private confirmDialogService: ConfirmationDialogSevice,
              private fileUploadService: InstituteFileUploadService,
              private bloodGroupService: BloodGroupService,
              private nationalityService: NationalityService,
              private maritalStatusService: MaritalStatusService,
              private datePipe: DatePipe,
              private message: NzMessageService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Staff', ClientUrl.STAFF),
      new BreadcrumbModel('Create', ClientUrl.STAFF + '/create')
    );
  }

  ngOnInit(): void {
    this.staffCreateForm = this.formBuilder.group({
      staffFirstName: ['', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50)
      ]],
      staffLastName: ['', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50)
      ]],
      staffDOB: [this._staffDOB, Validators.required],
      staffId: ['', Validators.required],
      currentAddress: ['', Validators.required],
      permanentAddress: ['', [
        Validators.required
      ]],
      primaryContact: ['', [
        Validators.required
      ]],
      secondaryContact: [''],
      joiningDate: [this._joiningDate,
        [
          Validators.required
        ]],
      qualification: ['', Validators.required],
      designation: [''],
      staffGender: [this._gender, Validators.required],
      experienceSummary: ['']
    });

    this.getStaffType();
    this.getBloodGroup();
    this.getNationality();
    this.getMaritalStatusList();
  }

  get f() {
    return this.staffCreateForm.controls;
  }

  getStaffType() {
    this.staffService.getStaffType()
      .subscribe(data => {
        this.staffTypes = data;
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

  getMaritalStatusList() {
    this.maritalStatusService.getAllMaritalStatus()
      .subscribe(data => {
        this.maritalStatusList = data;
      });
  }

  staffTypeSelectEvent(selectedId: any, selectedName: any) {
    if (selectedId !== '-1') {
      this.typeSelectId = selectedId;
      this.typeSelectName = selectedName;
    }
  }

  staffDOBEvent(result: Date): void {
    this._staffDOB = result;
  }

  joiningDateEvent(result: Date): void {
    this._staffDOB = result;
  }

  fileSelected(event: any) {
    this.selectedFile = <File>event.target.files[0];
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

  maritalStatusSelectEvent(selectedId: any, selectedName: any) {
    if (selectedId !== '-1') {
      this.maritalStatusSelectedId = selectedId;
      this.maritalStatusSelectedName = selectedName;
    }
  }

  onSubmit() {
    this.submitted = true;
    if (this.staffCreateForm.invalid) {
      return;
    }
    this.confirmDialog();
  }

  private confirmDialog() {
    const displayList: DisplayListModel[] = [];
    displayList.push(
      new DisplayListModel('Staff ID', this.staffCreateForm.value.staffId),
      new DisplayListModel('Staff Name', this.staffCreateForm.value.staffFirstName + ' '
        + this.staffCreateForm.value.staffLastName),

      new DisplayListModel('Date OF Birth(AD)', this.datePipe.transform(this.staffCreateForm.value.staffDOB, 'mediumDate')),
      new DisplayListModel('Gender', this.staffCreateForm.value.staffGender));
    if (this.bloodGroupSelectedName !== '') {
      displayList.push(new DisplayListModel('Blood Group', this.bloodGroupSelectedName));
    }
    if (this.maritalStatusSelectedName !== '') {
      displayList.push(new DisplayListModel('Marital Status', this.maritalStatusSelectedName));
    }
    displayList.push(new DisplayListModel('Nationality', this.nationalitySelectedName),
      new DisplayListModel('Current Address', this.staffCreateForm.value.currentAddress),
      new DisplayListModel('Permanent Address', this.staffCreateForm.value.permanentAddress),
      new DisplayListModel('Primary Contact', this.staffCreateForm.value.primaryContact),
      new DisplayListModel('Secondary Contact', this.staffCreateForm.value.secondaryContact),
      new DisplayListModel('Joining Date(AD)', this.datePipe.transform(this.staffCreateForm.value.joiningDate, 'mediumDate')),
      new DisplayListModel('Qualification', this.staffCreateForm.value.qualification),
      new DisplayListModel('Role', this.typeSelectName),
      new DisplayListModel('Designation', this.staffCreateForm.value.designation),
      new DisplayListModel('Experience Summary', this.staffCreateForm.value.experienceSummary));

    this.confirmDialogService.confirm('Confirm', 'Cancel', displayList)
      .then((confirmed) => {
        this.processRequest();
      })
      .catch(() => console.log('User dismissed the dialog'));
  }

  private processRequest() {
    const staffModel = new StaffCreateModel(
      this.staffCreateForm.value.staffId,
      this.staffCreateForm.value.staffFirstName,
      this.staffCreateForm.value.staffLastName,
      this.staffCreateForm.value.staffDOB,
      this.staffCreateForm.value.staffGender === 'Male' ? 1 : 2,
      this.staffCreateForm.value.currentAddress,
      this.staffCreateForm.value.permanentAddress,
      this.staffCreateForm.value.joiningDate,
      this.staffCreateForm.value.primaryContact,
      this.staffCreateForm.value.secondaryContact,
      this.typeSelectId,
      this.staffCreateForm.value.qualification,
      this.staffCreateForm.value.designation,
      this.bloodGroupSelectedId,
      this.nationalitySelectedId,
      this.staffCreateForm.value.experienceSummary,
      this.maritalStatusSelectedId
    );

    this.staffService.createNewStaff(staffModel)
      .subscribe(data => {
        this.uploadFile(data.id);
      });
    this.submitted = false;
  }

  private uploadFile(studentId: number) {
    console.log('Sending file for upload');
    this.fileUploadService.uploadFile(this.selectedFile, studentId, DocumentType.STAFF_PICTURE)
      .subscribe(event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress.percentage = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.router.navigateByUrl(ClientUrl.STAFF);
          this.message.success('New staff \'<b>' + this.staffCreateForm.value.staffFirstName
            + '</b>\' detail has been registered successfully', {nzDuration: 5000});
          this.onCancel();
        }
      });
  }

  onCancel() {
    this.staffCreateForm.reset();
  }
}
