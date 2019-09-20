import {
  Component,
  OnInit
} from '@angular/core';
import {routerTransition} from '../../../router.animations';
import {
  FormBuilder, FormControl,
  FormGroup,
  ValidationErrors,
  Validators
} from '@angular/forms';
import {
  ActivatedRoute,
  Router
} from '@angular/router';
import {
  HttpEventType,
  HttpResponse
} from '@angular/common/http';

import {PasswordValidator} from '../../../shared/validators/password.validator';
import {AdminSchoolService} from '../core/service/admin-school.service';
import {ConfirmationDialogSevice} from '../../../shared/components/confirmation-dailog/confirmation-dialog.sevice';
import {AdminURL} from '../../../shared/constants/admin-url';
import {DisplayListModel} from '../../../shared/components/confirmation-dailog/display-list.model';
import {AdminAddSchoolModel} from '../core/model/admin-add-school.model';
import {UserCreateModel} from '../core/model/user-create.model';
import {AdminSchoolTypeModel} from '../core/model/admin-school-type.model';
import {AddressService} from '../../../shared/services/address.service';
import {AddressListModel} from '../../../shared/model/address-list.model';
import {ContactCreateModel} from '../core/model/contact-create.model';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {AdminSchoolDetailModel} from '../core/model/admin-school-detail.model';
import {AdminFileUploadService} from '../core/service/admin-file-upload.service';

@Component({
  selector: 'app-admin-school-add',
  templateUrl: './admin-school-add.component.html',
  styleUrls: ['./admin-school.scss'],
  animations: [routerTransition()]
})
export class AdminSchoolAddComponent implements OnInit {
  // fileUploader: FileUploaderComponent;

  schoolAddForm: FormGroup;
  submitted = false;
  error = '';
  route: BreadcrumbModel[] = [];

  usernamePattern = '^(?=.*[9][6-8][0-9]{8}$)|' +
    '(?=.*^(([^<>()[\\]\\\\.,;:\\s@\\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\\"]+)*)|(\\".+\\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$)';

  schoolTypes: AdminSchoolTypeModel[];

  stateAddresses: AddressListModel[];
  districtAddresses: AddressListModel[];

  schoolId: any = '';
  isEdit: boolean = false;

  selectedFile: File = null;
  progress: { percentage: number } = {percentage: 0};

  // uploader: FileUploader = new FileUploader({url: AdminApi.FILE, removeAfterUpload: true, autoUpload: false, itemAlias: 'logo'});

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private schoolService: AdminSchoolService,
              private addressService: AddressService,
              private confirmDialogService: ConfirmationDialogSevice,
              private documentService: AdminFileUploadService) {

    this.route.push(
      new BreadcrumbModel('Dashboard', AdminURL.DASHBOARD),
      new BreadcrumbModel('School List', AdminURL.SCHOOL),
      new BreadcrumbModel('Create', AdminURL.SCHOOL + '/create')
    );
  }

  ngOnInit() {
    this.buildForm();
    this.onCancel();
    this.getSchoolDetail();
  }

  buildForm() {
    this.schoolAddForm = this.formBuilder.group({
        schoolName: ['', [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(50)
        ]],
        schoolLandline: [''],
        schoolWebsite: [''],
        schoolPrincipal: ['', [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(50)
        ]],
        schoolAddress: ['', [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(50)
        ]],
        contactPersonFullname: ['', [
          Validators.maxLength(50)
        ]],
        contactPersonDesignation: ['', [
          Validators.maxLength(50)
        ]],
        contactPersonLandline: ['', [
          Validators.maxLength(50)
        ]],
        contactPersonMobileNumber: ['', [
          Validators.maxLength(50)
        ]],
        contactPersonEmailId: ['', [
          Validators.maxLength(50)
        ]],
        schoolUsername: ['', [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(50)
        ]],
        schoolUserPassword: ['', [
          Validators.required,
          Validators.maxLength(30),
          Validators.minLength(6)
        ]],
        schoolUserConfirmPassword: ['', Validators.required],
        schoolRegistrationPrefix: ['', {
          validators: [
            Validators.required,
            Validators.minLength(3),
            Validators.maxLength(6)
          ],
        }]

      },
      {
        validators: PasswordValidator('schoolUserPassword', 'schoolUserConfirmPassword')
      });
  }

  get f() {
    return this.schoolAddForm.controls;
  }

  getFormValidationErrors() {
    Object.keys(this.schoolAddForm.controls).forEach(key => {

      const controlErrors: ValidationErrors = this.schoolAddForm.get(key).errors;
      if (controlErrors != null) {
        Object.keys(controlErrors).forEach(keyError => {
          console.log('Key control: ' + key + ', keyError: ' + keyError + ', err value: ', controlErrors[keyError]);
        });
      }
    });
  }

  getSchoolDetail() {
    this.schoolId = this.activatedRoute.snapshot.paramMap.get('institute_id');
    if (this.schoolId !== null && this.schoolId !== 0) {
      this.isEdit = true;
      this.schoolAddForm.controls['schoolUsername'].setValidators(null);
      this.schoolAddForm.controls['schoolUserPassword'].setValidators(null);
      this.schoolAddForm.controls['schoolUserConfirmPassword'].setValidators(null);

      this.schoolService.getSchoolDetail(this.schoolId)
        .subscribe(data => {
          this.populateDetail(data);
        });
    }
  }

  fileSelected(event: any) {
    this.selectedFile = <File>event.target.files[0];
  }

  registrationPrefixCheck() {
    const value = this.schoolAddForm.value.schoolRegistrationPrefix;
    if (value.length > 2) {
      this.schoolService.checkRegistrationPrefix(value)
        .subscribe(data => {
          console.log('Reg check reply ', data);
          if (!data || (data.exist === true)) {
            this.schoolAddForm.controls['schoolRegistrationPrefix'].setErrors({'exist': true});
          } else {
            this.schoolAddForm.controls['schoolRegistrationPrefix'].setErrors(null);
          }
        }, error => {
          console.log('Error ' + error);
        });
    }
  }

  onSubmit() {
    this.submitted = true;
    if (this.schoolAddForm.invalid) {
      this.submitted = false;
      this.getFormValidationErrors();
      return;
    }
    this.confirmDialog();
  }

  public confirmDialog() {
    const scLandline = new DisplayListModel('Landline', this.schoolAddForm.value.schoolLandline);
    const scWebsite = new DisplayListModel('Website', this.schoolAddForm.value.schoolWebsite);
    const cpFullname = new DisplayListModel('Contact Person', this.schoolAddForm.value.contactPersonFullname);
    const cpDesignation = new DisplayListModel('Designation', this.schoolAddForm.value.contactPersonDesignation);
    const cpMobile = new DisplayListModel('Contact Person Mobile', this.schoolAddForm.value.contactPersonMobileNumber);
    const cpLandline = new DisplayListModel('Contact Person Landline', this.schoolAddForm.value.contactPersonLandline);
    const cpEmail = new DisplayListModel('Contact Person Email', this.schoolAddForm.value.contactPersonEmailId);
    const scUsername = new DisplayListModel('Username', this.schoolAddForm.value.schoolUsername);

    const displayList: DisplayListModel[] = [];
    displayList.push(new DisplayListModel('Name', this.schoolAddForm.value.schoolName),
      new DisplayListModel('Principal', this.schoolAddForm.value.schoolPrincipal),
      new DisplayListModel('Principal', this.schoolAddForm.value.schoolRegistrationPrefix));
    if (this.schoolAddForm.value.schoolLandline) {
      displayList.push(scLandline);
    }
    if (this.schoolAddForm.value.schoolWebsite) {
      displayList.push(scWebsite);
    }
    displayList.push(new DisplayListModel('Address', this.schoolAddForm.value.schoolAddress));

    if (this.schoolAddForm.value.contactPersonFullname) {
      displayList.push(cpFullname);
    }
    if (this.schoolAddForm.value.contactPersonDesignation) {
      displayList.push(cpDesignation);
    }
    if (this.schoolAddForm.value.contactPersonMobileNumber) {
      displayList.push(cpMobile);
    }
    if (this.schoolAddForm.value.contactPersonLandline) {
      displayList.push(cpLandline);
    }
    if (this.schoolAddForm.value.contactPersonEmailId) {
      displayList.push(cpEmail);
    }
    displayList.push(scUsername);

    this.confirmDialogService.confirm('Confirm', 'Cancel', displayList)
      .then((confirmed) => {
        this.processRequest();
      })
      .catch(e => {
        console.error('Error on submit: ' + e);
      });
  }

  onCancel() {
    this.error = '';
    this.submitted = false;
    this.schoolAddForm.reset();
  }

  public processRequest() {
    const schoolModel = new AdminAddSchoolModel();
    schoolModel.name = this.schoolAddForm.value.schoolName;
    schoolModel.landline = this.schoolAddForm.value.schoolLandline;
    schoolModel.website = this.schoolAddForm.value.schoolWebsite;
    schoolModel.principal = this.schoolAddForm.value.schoolPrincipal;

    schoolModel.address = this.schoolAddForm.value.schoolAddress;
    schoolModel.area = this.schoolAddForm.value.schoolAddressArea;
    schoolModel.registration_no_prefix= this.schoolAddForm.value.schoolRegistrationPrefix;

    schoolModel.contact = new ContactCreateModel(
      this.schoolAddForm.value.contactPersonFullname,
      this.schoolAddForm.value.contactPersonMobileNumber,
      this.schoolAddForm.value.contactPersonEmailId,
      this.schoolAddForm.value.contactPersonLandline,
      this.schoolAddForm.value.contactPersonDesignation
    );
    schoolModel.user = new UserCreateModel(
      this.schoolAddForm.value.schoolUsername,
      this.schoolAddForm.value.schoolUserPassword);

    if (this.isEdit) {
      this.schoolService.updateSchoolStatus(schoolModel)
        .subscribe(data => {
            this.router.navigateByUrl(AdminURL.SCHOOL);
          },
          error => {
            this.error = error;
          });
    } else {

      this.schoolService.createSchool(schoolModel)
        .subscribe(data => {
            this.uploadFile(data.id);
          },
          error => {
            this.error = error;
          });
    }
  }

  private uploadFile(instituteId: number) {
    this.documentService.uploadFile(this.selectedFile, instituteId)
      .subscribe(event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress.percentage = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.onCancel();
          this.router.navigateByUrl(AdminURL.SCHOOL);
        }
      });
  }

  private populateDetail(detail: AdminSchoolDetailModel) {
    this.schoolAddForm.controls['schoolName'].setValue(detail.name);
    this.schoolAddForm.controls['schoolLandline'].patchValue(detail.landline);
    this.schoolAddForm.controls['schoolWebsite'].setValue(detail.website);
    this.schoolAddForm.controls['schoolPrincipal'].setValue(detail.principal);
    this.schoolAddForm.controls['schoolAddress'].setValue(detail.address);
  }

}
