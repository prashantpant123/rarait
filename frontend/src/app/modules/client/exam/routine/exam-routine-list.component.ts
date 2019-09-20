import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';

import {routerTransition} from '../../../../router.animations';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {ExamRoutineListModel} from '../../core/model/exam/routine/exam-routine-list.model';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ExamRoutineService} from '../../core/service/exam/exam-routine.service';
import {ClassDropdownModel} from '../../core/model/class/class-dropdown.model';
import {ClassService} from '../../core/service/class.service';
import {ExamService} from '../../core/service/exam/exam.service';
import {ExamDropdownModel} from '../../core/model/exam/exam-dropdown.model';
import {ExamRoutineCreateModel} from '../../core/model/exam/routine/exam-routine-create.model';
import {ExamRoutineCreateListModel} from '../../core/model/exam/routine/exam-routine-create-list.model';

@Component({
  selector: 'app-exam-routine-list',
  templateUrl: './exam-routine-list.component.html',
  animations: [routerTransition()]
})
export class ExamRoutineListComponent implements OnInit {

  route: BreadcrumbModel[] = [];
  examRoutineList: ExamRoutineListModel[] = [];
  examList: ExamDropdownModel[] = [];
  classList: ClassDropdownModel[] = [];
  dataSet: ExamRoutineCreateModel[] = [];
  cacheData = {};

  submitting = false;
  _loading = false;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  examValue = '0';
  classValue = '0';
  classSelectedId: number = 0;
  examSelectedId: number = 0;

  modalVisible = false;
  isOkLoading = false;

  constructor(
    private examRoutineService: ExamRoutineService,
    private examService: ExamService,
    private classService: ClassService,
    private router: Router,
    private datePipe: DatePipe
  ) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Exam', ClientUrl.EXAM),
      new BreadcrumbModel('Routine', ClientUrl.EXAM_ROUTINE)
    );
  }

  ngOnInit() {
    this.getExamList();
    this.getClassList();
  }

  private getClassList() {
    this.classService.getClassDropdown()
      .subscribe(data => {
        this.classList = data;
      }, error => {
        console.log('Error: ', error);
      });
  }

  private getExamList() {
    this.examService.getExamAndSessionDropdown()
      .subscribe(data => {
        this.examList = data;
      }, error => {
        console.log('Error: ', error);
      });
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getExamRoutineList();
  }

  private getExamRoutineList() {
    this._loading = true;
    this.examRoutineService.getExamRoutineList(this.examSelectedId, this.classSelectedId)
      .subscribe(data => {
        this.examRoutineList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this.initCache();
        this._loading = false;
      });
  }

  classSelectEvent(selectedId: number) {
    if (selectedId !== 0) {
      this.classSelectedId = selectedId;
      if (this.examSelectedId !== 0) {
        this.getExamRoutineList();
      }
    }
  }

  examSelectEvent(selectedId: number) {
    if (selectedId !== 0) {
      this.examSelectedId = selectedId;
      if (this.classSelectedId !== 0) {
        this.getExamRoutineList();
      }
    }
  }

  onExamDateChange(date: Date) {
    console.log('Date ', date);
  }

  private initCache() {
    this.examRoutineList.forEach(item => {
      this.cacheData[item.subject_id] = {
        data: {...item}
      };
    });
  }

  onSubmit(): void {
    this.modalVisible = true;
  }

  modalConfirm() {
    this.isOkLoading = true;
    this.submitting = true;
    this.examRoutineList.forEach(item => {
      const data = this.cacheData[item.subject_id].data;
      const startTime: Date = data.start_time;
      const endTime: Date = data.end_time;
      this.dataSet.push(new ExamRoutineCreateModel(
        data.id,
        data.subject_id,
        data.exam_date,
        (startTime.getHours() < 10 ? '0' + startTime.getHours() : startTime.getHours())
        + ':' + (startTime.getMinutes() < 10 ? '0' + startTime.getMinutes() : startTime.getMinutes()),
        (endTime.getHours() < 10 ? '0' + endTime.getHours() : endTime.getHours())
        + ':' + (endTime.getMinutes() < 10 ? '0' + endTime.getMinutes() : endTime.getMinutes()),
        data.remarks,
        data.full_mark,
        data.pass_mark));
    });
    console.log('datas: ', this.dataSet);
    const request = new ExamRoutineCreateListModel;
    request.class_id = this.classSelectedId;
    request.exam_id = this.examSelectedId;
    request.data = this.dataSet;

    console.log('after data: ', request);

    this.examRoutineService.createNewExamRoutine(request)
      .subscribe(data => {
        this.router.navigateByUrl(ClientUrl.EXAM_ROUTINE);
      });
    this.submitting = false;
    this.isOkLoading = false;
  }

  modalCancel(): void {
    this.modalVisible = false;
  }


}
