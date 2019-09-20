import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

import {ConfirmationDialogSevice} from '../../../shared/components/confirmation-dailog/confirmation-dialog.sevice';
import {DisplayListModel} from '../../../shared/components/confirmation-dailog/display-list.model';
import {ClientUrl} from '../../../shared/constants/client-url';
import {SubjectService} from '../core/service/subject.service';
import {SubjectCreateModel} from '../core/model/subject/subject-create.model';
import {routerTransition} from '../../../router.animations';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {ClassService} from '../core/service/class.service';
import {ClassDropdownModel} from '../core/model/class/class-dropdown.model';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-subject',
  templateUrl: './subject-add.component.html',
  animations: [routerTransition()]
})
export class SubjectAddComponent implements OnInit {

  subjectCreateForm: FormGroup;
  submitted = false;
  route: BreadcrumbModel[] = [];

  clazzList: ClassDropdownModel[] = [];
  classSelectedId: number;
  classSelectedName: string;
  _optional = 'No';
  _practical = 'No';
  hiddenPractical = true;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private subjectService: SubjectService,
              private classService: ClassService,
              private confirmDialogService: ConfirmationDialogSevice,
              private message: NzMessageService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Subjects', ClientUrl.SUBJECT),
      new BreadcrumbModel('Create', ClientUrl.SUBJECT + '/create')
    );
  }

  ngOnInit() {
    this.subjectCreateForm = this.formBuilder.group({
      subjectName: ['', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(100)
      ]],
      subjectCode: ['', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50)
      ]],
      subjectDescription: [''],
      subjectFullMark: ['', [
        Validators.required
      ]],
      subjectPassMark: ['', [
        Validators.required
      ]],
      isOptional: [this._optional, Validators.required],
      hasPractical: [this._practical, Validators.required],
      subjectPracticalFullMark: [''],
      subjectPracticalPassMark: [''],
    });

    this.subjectCreateForm.controls['hasPractical'].valueChanges
      .subscribe(data => {
        if (!data || (typeof data === 'string' && data === 'Yes')) {
          this.subjectCreateForm.patchValue({subjectPracticalFullMark: 0}, {emitEvent: true});
          this.subjectCreateForm.patchValue({subjectPracticalPassMark: 0}, {emitEvent: true});
          this.subjectCreateForm.controls['subjectPracticalFullMark']
            .setValidators([Validators.required]);
          this.subjectCreateForm.controls['subjectPracticalPassMark']
            .setValidators([Validators.required]);
          this.hiddenPractical = false;
        } else {
          this.subjectCreateForm.controls['subjectPracticalFullMark'].reset();
          this.subjectCreateForm.controls['subjectPracticalFullMark'].setValidators([]);
          this.subjectCreateForm.controls['subjectPracticalPassMark'].reset();
          this.subjectCreateForm.controls['subjectPracticalPassMark'].setValidators([]);
          this.hiddenPractical = true;
        }
      });

    this.getClassList();
  }

  getClassList() {
    this.classService.getClassDropdown()
      .subscribe(data => {
        this.clazzList = data;
      });
  }

  onSubmit() {
    this.submitted = true;
    if (this.subjectCreateForm.invalid) {
      return;
    }
    this.confirmDialog();

  }

  get f() {
    return this.subjectCreateForm.controls;
  }

  classSelectEvent(selectedId: any, selectedName: any) {
    if (selectedId !== '0') {
      this.classSelectedId = selectedId;
      this.classSelectedName = selectedName;
    }
  }

  private confirmDialog() {
    const displayList: DisplayListModel[] = [];
    displayList.push(new DisplayListModel('Class', this.classSelectedName),
      new DisplayListModel('Subject Name', this.subjectCreateForm.value.subjectName),
      new DisplayListModel('Subject Code', this.subjectCreateForm.value.subjectCode));
    if (this.subjectCreateForm.value.subjectDescription !== null) {
      displayList.push(new DisplayListModel('Description', this.subjectCreateForm.value.subjectDescription));
    }
    displayList.push(
      new DisplayListModel('Full Mark', this.subjectCreateForm.value.subjectFullMark),
      new DisplayListModel('Pass Mark', this.subjectCreateForm.value.subjectPassMark),
      new DisplayListModel('Is Optional', this.subjectCreateForm.value.isOptional));

    if (this.subjectCreateForm.value.hasPractical === 'Yes') {
      displayList.push(
        new DisplayListModel('Practical Full Mark', this.subjectCreateForm.value.subjectPracticalFullMark),
        new DisplayListModel('Practical Pass Mark', this.subjectCreateForm.value.subjectPracticalPassMark));
    }

    this.confirmDialogService.confirm('Confirm', 'Cancel', displayList)
      .then((confirmed) => {
        this.processRequest();
      })
      .catch(() => console.log('User dismissed the dialog'));
  }

  private processRequest() {
    const subjectModel = new SubjectCreateModel(
      this.classSelectedId,
      this.subjectCreateForm.value.subjectName,
      this.subjectCreateForm.value.subjectCode,
      this.subjectCreateForm.value.subjectDescription,
      this.subjectCreateForm.value.subjectFullMark,
      this.subjectCreateForm.value.subjectPassMark,
      this.subjectCreateForm.value.isOptional === 'Yes' ? true : false,
      this.subjectCreateForm.value.hasPractical === 'Yes' ? true : false,
      this.subjectCreateForm.value.subjectPracticalFullMark,
      this.subjectCreateForm.value.subjectPracticalPassMark,
    );

    this.subjectService.createNewSubject(subjectModel)
      .subscribe(data => {
        this.router.navigateByUrl(ClientUrl.SUBJECT);
        this.message.success('New subject \'<b>' + this.subjectCreateForm.value.subjectName + '</b>\' has been added successfully',
          {nzDuration: 5000});
        this.onCancel();
      });
  }

  onCancel() {
    this.subjectCreateForm.reset();
  }

}
