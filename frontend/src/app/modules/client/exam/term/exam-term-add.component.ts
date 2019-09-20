import {Component, OnInit} from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import {Router} from '@angular/router';

import {routerTransition} from '../../../../router.animations';
import {ConfirmationDialogSevice} from '../../../../shared/components/confirmation-dailog/confirmation-dialog.sevice';
import {ClientUrl} from '../../../../shared/constants/client-url';

import {DisplayListModel} from '../../../../shared/components/confirmation-dailog/display-list.model';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ExamTermService} from '../../core/service/exam/exam-term.service';
import {ExamTermCreateModel} from '../../core/model/exam/term/exam-term-create.model';

@Component({
  selector: 'app-exam-term-add',
  templateUrl: './exam-term-add.component.html',
  animations: [routerTransition()]
})
export class ExamTermAddComponent implements OnInit {

  examTermAddForm: FormGroup;
  submitted = false;
  route: BreadcrumbModel[] = [];

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private confirmDialogService: ConfirmationDialogSevice,
              private examTermService: ExamTermService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Exam', ClientUrl.EXAM),
      new BreadcrumbModel('Terms', ClientUrl.TERM),
      new BreadcrumbModel('Create', ClientUrl.TERM + '/create')
    );
  }

  ngOnInit() {
    this.examTermAddForm = this.formBuilder.group({
      examTermName: ['', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50)
      ]],
      termWeightage: ['', [Validators.required]]
    });

  }

  get f() {
    return this.examTermAddForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.examTermAddForm.invalid) {
      return;
    }
    this.confirmDialog();
  }

  public confirmDialog() {
    const displayList: DisplayListModel[] = [];
    displayList.push(new DisplayListModel('Term Name', this.examTermAddForm.value.examTermName),
      new DisplayListModel('Term Weightage', this.examTermAddForm.value.termWeightage));

    this.confirmDialogService.showDialog(displayList)
      .then((confirmed) => {
        this.processRequest();
      })
      .catch(() => console.log('User dismissed the dialog'));
  }

  private processRequest() {
    const model = new ExamTermCreateModel(
      this.examTermAddForm.value.examTermName,
      this.examTermAddForm.value.termWeightage);

    this.examTermService.createNewExamTerm(model)
      .subscribe(data => {
        this.router.navigateByUrl(ClientUrl.TERM);
        this.onCancel();
      });
  }

  onCancel() {
    this.examTermAddForm.reset();
  }

}
