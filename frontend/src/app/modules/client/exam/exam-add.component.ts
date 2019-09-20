import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

import {routerTransition} from '../../../router.animations';
import {ConfirmationDialogSevice} from '../../../shared/components/confirmation-dailog/confirmation-dialog.sevice';
import {ExamService} from '../core/service/exam/exam.service';
import {ClientUrl} from '../../../shared/constants/client-url';
import {DisplayListModel} from '../../../shared/components/confirmation-dailog/display-list.model';
import {ExamCreateModel} from '../core/model/exam/exam-create.model';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {AcademicSessionDropdownModel} from '../core/model/academic-session/academic-session-dropdown.model';
import {AcademicSessionService} from '../core/service/academic-session.service';
import {DatePipe} from '@angular/common';
import {ExamTermService} from '../core/service/exam/exam-term.service';
import {DropdownModel} from '../../../shared/model/dropdown.model';

@Component({
  selector: 'app-exam-add',
  templateUrl: './exam-add.component.html',
  animations: [routerTransition()]
})
export class ExamAddComponent implements OnInit {

  examAddForm: FormGroup;
  submitted = false;
  route: BreadcrumbModel[] = [];

  sessionId: number = 0;
  sessionName: string;
  termId: number = 0;
  termName: string;
  academicSessionList: AcademicSessionDropdownModel[] = [];
  examTermList: DropdownModel[] = [];
  start_date: Date;
  end_date: Date;
  _size = 'large';
  dateRange = [];

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private confirmDialogService: ConfirmationDialogSevice,
              private examService: ExamService,
              private examTermService: ExamTermService,
              private academicSessionService: AcademicSessionService,
              private datePipe: DatePipe) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Exams', ClientUrl.EXAM),
      new BreadcrumbModel('Create', ClientUrl.EXAM + '/create')
    );
  }

  ngOnInit() {
    this.examAddForm = this.formBuilder.group({
      examDateRange: ['', [
        Validators.required
      ]],
      weightage: ['', [Validators.required]]
    });
    this.getExamTerms();
    this.getAcademicSessions();
  }

  get f() {
    return this.examAddForm.controls;
  }

  getAcademicSessions() {
    this.academicSessionService.getSessionList()
      .subscribe(data => {
        this.academicSessionList = data;
      });
  }

  academicSessionEvent(selectId: any, selectVal: any) {
    if (selectId !== '0') {
      this.sessionId = selectId;
      this.sessionName = selectVal;
    }
  }

  getExamTerms() {
    this.examTermService.getExamTermDropdown()
      .subscribe(data => {
        this.examTermList = data;
      });
  }

  examTermSessionEvent(selectId: any, selectValue: any) {
    if (selectId !== '0') {
      this.termId = selectId;
      this.termName = selectValue;
    }
  }

  onDateRangeChange(result: Date[]): void {
    this.start_date = result[0];
    this.end_date = result[1];
  }

  onSubmit() {
    this.submitted = true;
    if (this.examAddForm.invalid) {
      console.log('Invalid: ' + this.examAddForm.errors);
      return;
    }
    this.confirmDialog();
  }

  public confirmDialog() {
    const displayList: DisplayListModel[] = [];
    displayList.push(
      new DisplayListModel('Academic Session', this.sessionName),
      new DisplayListModel('Exam Name', this.termName),
      new DisplayListModel('Start Date(AD)', this.datePipe.transform(this.start_date, 'dd-MM-yyyy')),
      new DisplayListModel('End Date(AD)', this.datePipe.transform(this.end_date, 'dd-MM-yyyy')),
      new DisplayListModel('Weightage', this.examAddForm.value.weightage)
    );

    this.confirmDialogService.confirm('Confirm', 'Cancel', displayList)
      .then((confirmed) => {
        this.processRequest();
      })
      .catch(() => console.log('User dismissed the dialog'));
  }

  private processRequest() {
    const sessionModel = new ExamCreateModel(
      this.termId,
      this.start_date,
      this.end_date,
      this.examAddForm.value.weightage,
      this.sessionId
    );

    this.examService.createNewExam(sessionModel)
      .subscribe(data => {
        this.onCancel();
        this.router.navigateByUrl(ClientUrl.EXAM);
      });
  }

  onCancel() {
    this.examAddForm.reset();
  }

}
