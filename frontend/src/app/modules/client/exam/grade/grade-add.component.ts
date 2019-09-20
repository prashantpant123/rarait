import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

import {routerTransition} from '../../../../router.animations';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ConfirmationDialogSevice} from '../../../../shared/components/confirmation-dailog/confirmation-dialog.sevice';
import {GradeService} from '../../core/service/exam/grade.service';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {DisplayListModel} from '../../../../shared/components/confirmation-dailog/display-list.model';
import {GradeCreateModel} from '../../core/model/exam/grade/grade-create.model';

@Component({
  selector: 'app-grade-add',
  templateUrl: './grade-add.component.html',
  animations: [routerTransition()]
})
export class GradeAddComponent implements OnInit {

  gradeAddForm: FormGroup;
  submitted = false;
  route: BreadcrumbModel[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private confirmDialogService: ConfirmationDialogSevice,
    private gradeService: GradeService
  ) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Grade', ClientUrl.GRADE),
      new BreadcrumbModel('Create', ClientUrl.GRADE + '/create')
    );
  }

  ngOnInit() {
    this.gradeAddForm = this.formBuilder.group({
      gradeValue: ['', [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(10)
      ]],
      lowMark: ['', [
        Validators.required
      ]],
      highMark: ['', [Validators.required]]
    });
  }

  get f() {
    return this.gradeAddForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.gradeAddForm.invalid) {
      return;
    }
    this.confirmDialog();
  }

  public confirmDialog() {
    const displayList: DisplayListModel[] = [];
    displayList.push(
      new DisplayListModel('Grade Value', this.gradeAddForm.value.gradeValue),
      new DisplayListModel('Low Mark Value', this.gradeAddForm.value.lowMark),
      new DisplayListModel('High Mark Value', this.gradeAddForm.value.highMark),
    );

    this.confirmDialogService.confirm('Confirm', 'Cancel', displayList)
      .then((confirmed) => {
        this.processRequest();
      })
      .catch(() => console.log('User dismissed the dialog'));
  }

  private processRequest() {
    const model = new GradeCreateModel(
      this.gradeAddForm.value.gradeValue,
      this.gradeAddForm.value.lowMark,
      this.gradeAddForm.value.highMark
    );

    this.gradeService.createGrade(model)
      .subscribe(data => {
        this.router.navigateByUrl(ClientUrl.GRADE);
        this.onCancel();
      });
  }

  onCancel() {
    this.gradeAddForm.reset();
  }

}
