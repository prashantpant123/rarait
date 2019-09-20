import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

import {routerTransition} from '../../../../router.animations';
import {ConfirmationDialogSevice} from '../../../../shared/components/confirmation-dailog/confirmation-dialog.sevice';
import {ExamService} from '../../core/service/exam/exam.service';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {DisplayListModel} from '../../../../shared/components/confirmation-dailog/display-list.model';
import {ExamRoutineCreateModel} from '../../core/model/exam/routine/exam-routine-create.model';
import {ExamRoutineService} from '../../core/service/exam/exam-routine.service';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';

@Component({
  selector: 'app-exam-routine-add',
  templateUrl: './exam-routine-add.component.html',
  animations: [routerTransition()]
})
export class ExamRoutineAddComponent implements OnInit {

  examRoutineAddForm: FormGroup;
  submitted = false;
  route: BreadcrumbModel[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private confirmDialogService: ConfirmationDialogSevice,
    private examRoutineService: ExamRoutineService
  ) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Exam', ClientUrl.EXAM),
      new BreadcrumbModel('Routine', ClientUrl.EXAM_ROUTINE)
    );
  }

  ngOnInit() {
    this.examRoutineAddForm = this.formBuilder.group({
      subjectId: ['', [Validators.required]],
      fullMark: ['', [Validators.required]],
      passMark: ['', [Validators.required]],
      dateTime: ['', [Validators.required]],
      duration: ['', [Validators.required]]
    });
  }

  get f() {
    return this.examRoutineAddForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.examRoutineAddForm.invalid) {
      console.log('Error on school add validation: ' + this.examRoutineAddForm.value);
      return;
    }
    this.confirmDialog();
    this.onCancel();
  }

  public confirmDialog() {
    const erSubjectId = new DisplayListModel('Subject Name: ', this.examRoutineAddForm.value.transportRouteId);
    const erFullMark = new DisplayListModel('Full Mark: ', this.examRoutineAddForm.value.fullMark);
    const erPassMark = new DisplayListModel('Pass Mark: ', this.examRoutineAddForm.value.passMark);
    const erDateTime = new DisplayListModel('Date/Time: ', this.examRoutineAddForm.value.dateTime);
    const erDuration = new DisplayListModel('Exam Duration(Hrs): ', this.examRoutineAddForm.value.duration);

    let displayList: DisplayListModel[] = [];
    displayList.push(erSubjectId);
    displayList.push(erFullMark);
    displayList.push(erPassMark);
    displayList.push(erDateTime);
    displayList.push(erDuration);

    this.confirmDialogService.confirm('Confirm', 'Cancel', displayList)
      .then((confirmed) => {
        // TODO: show processing untill request to server side is completed or timeout of 20s
        // this.processRequest();
      })
      .catch(() => console.log('User dismissed the dialog'));
  }

  // private processRequest() {
  //   const routineModel = new ExamRoutineCreateModel(
  //     this.examRoutineAddForm.value.transportRouteId,
  //     this.examRoutineAddForm.value.fullMark,
  //     this.examRoutineAddForm.value.passMark,
  //     this.examRoutineAddForm.value.dateTime,
  //     this.examRoutineAddForm.value.duration,
  //   );
  //
  //   this.examRoutineService.createNewExamRoutine(routineModel)
  //     .subscribe(data => {
  //       this.router.navigateByUrl(ClientUrl.EXAM_ROUTINE);
  //     });
  // }

  onCancel() {
    this.examRoutineAddForm.reset();
  }

}
