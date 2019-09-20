import {Component, OnInit} from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';

import {routerTransition} from '../../../router.animations';
import {AcademicSessionService} from '../core/service/academic-session.service';
import {DisplayListModel} from '../../../shared/components/confirmation-dailog/display-list.model';
import {ConfirmationDialogSevice} from '../../../shared/components/confirmation-dailog/confirmation-dialog.sevice';
import {ClientUrl} from '../../../shared/constants/client-url';
import {AcademicSessionModel} from '../core/model/academic-session/academic-session.model';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-academic-session-add',
  templateUrl: './academic-session-add.component.html',

  animations: [routerTransition()]
})
export class AcademicSessionAddComponent implements OnInit {

  academicSessionForm: FormGroup;
  submitted = false;
  route: BreadcrumbModel[] = [];
  _sessionStartDate: any;
  _sessionEndDate: any;
  _currentSession = 'Yes';
  start_date: Date;
  end_date: Date;
  _size = 'large';

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private academicSessionService: AcademicSessionService,
              private confirmDialogService: ConfirmationDialogSevice,
              private datePipe: DatePipe,
              private message: NzMessageService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Academic Session', ClientUrl.ACADEMIC_SESSION),
      new BreadcrumbModel('Create', ClientUrl.ACADEMIC_SESSION_CREATE)
    );
  }

  ngOnInit() {
    this.academicSessionForm = this.formBuilder.group({
      sessionName: ['', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(100)
      ]],
      sessionStartDate: [this._sessionStartDate, Validators.required],
      sessionEndDate: [this._sessionEndDate, [Validators.required]],
      currentSession: [this._currentSession, [Validators.required]]
    });
  }

  get f() {
    return this.academicSessionForm.controls;
  }

  onStartDateChange(result: Date): void {
    this.start_date = result;
  }

  onEndDateChange(result: Date): void {
    this.end_date = result;
  }

  onSubmit() {
    this.submitted = true;
    if (this.academicSessionForm.invalid) {
      return;
    }
    this.confirmDialog();
  }

  private confirmDialog() {
    const displayList: DisplayListModel[] = [];
    displayList.push(new DisplayListModel('Session Name', this.academicSessionForm.value.sessionName),
      new DisplayListModel('Session Start Date(AD)', this.datePipe.transform(this.start_date, 'mediumDate')),
      new DisplayListModel('Session End Date(AD)', this.datePipe.transform(this.end_date, 'mediumDate')),
      new DisplayListModel('Current Session', this.academicSessionForm.value.currentSession));

    this.confirmDialogService.showDialog(displayList)
      .then((confirmed) => {
        this.processRequest();
      })
      .catch(() => console.log('User dismissed the dialog'));
  }

  private processRequest() {
    const sessionModel = new AcademicSessionModel(
      this.academicSessionForm.value.sessionName,
      this.start_date,
      this.end_date,
      this.academicSessionForm.value.currentSession === 'Yes' ? true : false
    );

    this.academicSessionService.createNewSession(sessionModel)
      .subscribe(data => {
        this.router.navigateByUrl(ClientUrl.ACADEMIC_SESSION);
        this.message.success('New academic session <b>\'' + this.academicSessionForm.value.sessionName + '\'</b> has been added successfully');
        this.onCancel();
      }, error => console.log('Error ' + error));
  }

  onCancel() {
    this.academicSessionForm.reset();
  }

}
