import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

import {routerTransition} from '../../../router.animations';
import {ClassService} from '../core/service/class.service';
import {DisplayListModel} from '../../../shared/components/confirmation-dailog/display-list.model';
import {ClientUrl} from '../../../shared/constants/client-url';
import {ConfirmationDialogSevice} from '../../../shared/components/confirmation-dailog/confirmation-dialog.sevice';
import {ClassCreateModel} from '../core/model/class/class-create.model';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {AcademicSessionService} from '../core/service/academic-session.service';
import {DropdownModel} from '../../../shared/model/dropdown.model';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-class-add',
  templateUrl: './class-add.component.html',
  animations: [routerTransition()]
})
export class ClassAddComponent implements OnInit {

  classCreateForm: FormGroup;
  submitted = false;
  route: BreadcrumbModel[] = [];
  departmentList: DropdownModel[] = [];
  departmentSelectedId: number;
  departmentSelectedName: string;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private classService: ClassService,
              private academicSessionService: AcademicSessionService,
              private confirmDialogService: ConfirmationDialogSevice,
              private message: NzMessageService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Classes', ClientUrl.CLASS),
      new BreadcrumbModel('Create', ClientUrl.CLASS + '/create')
    );
  }

  ngOnInit() {
    this.classCreateForm = this.formBuilder.group({
      className: ['', Validators.required],
      classCode: ['', Validators.required]
    });
    this.getDepartmentList();
  }

  get f() {
    return this.classCreateForm.controls;
  }

  departmentSelectEvent(selectId: any, selectValue: any) {
    if (selectId !== '') {
      this.departmentSelectedId = selectId;
      this.departmentSelectedName = selectValue;
    }
  }

  getDepartmentList() {
    this.classService.getDepartmentDropdown()
      .subscribe(data => {
          this.departmentList = data;
        }, error => console.error(error)
      );
  }

  onSubmit() {
    this.submitted = true;
    if (this.classCreateForm.invalid) {
      return;
    }
    this.confirmDialog();

  }

  private confirmDialog() {
    const displayList: DisplayListModel[] = [];
    displayList.push(new DisplayListModel('Category: ', this.departmentSelectedName),
      new DisplayListModel('Class Name: ', this.classCreateForm.value.className),
      new DisplayListModel('Class Code: ', this.classCreateForm.value.classCode));

    this.confirmDialogService.confirm('Confirm', 'Cancel', displayList)
      .then((confirmed) => {
        // TODO: show processing untill request to server side is completed or timeout of 20s
        this.processRequest();
      })
      .catch(() => console.log('User dismissed the dialog'));
  }

  private processRequest() {
    const classModel = new ClassCreateModel(
      this.departmentSelectedId,
      this.classCreateForm.value.className,
      this.classCreateForm.value.classCode
    );

    this.classService.createNewClass(classModel)
      .subscribe(data => {
        this.router.navigateByUrl(ClientUrl.CLASS);
        this.message.success('New class \'<b>' + this.classCreateForm.value.className + '</b>\' has been added successfully');
        this.onCancel();
      });
  }


  onCancel() {
    this.classCreateForm.reset();
  }

}
