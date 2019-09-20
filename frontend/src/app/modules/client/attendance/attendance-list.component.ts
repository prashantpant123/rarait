import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';

import {routerTransition} from '../../../router.animations';

import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../shared/constants/client-url';
import {AttendanceService} from '../core/service/attendance.service';
import {AttendanceListModel} from '../core/model/attendance/attendance-list.model';
import {AttendanceDropdownModel} from '../core/model/attendance/attendance-dropdown.model';
import {ClassDropdownModel} from '../core/model/class/class-dropdown.model';
import {ClassService} from '../core/service/class.service';
import {AttendanceCreateListModel} from '../core/model/attendance/attendance-create-list.model';
import {AttendanceCreateModel} from '../core/model/attendance/attendance-create.model';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance-list.component.html',
  animations: [routerTransition()]
})
export class AttendanceListComponent implements OnInit {

  route: BreadcrumbModel[] = [];
  attendanceList: AttendanceListModel[] = [];
  classList: ClassDropdownModel[] = [];
  attendanceStatusList: AttendanceDropdownModel[] = [];
  dataSet: AttendanceCreateModel[] = [];
  cacheData = {};

  classValue = '0';
  classSelectedId: number = 0;

  submitting = false;
  _loading = false;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  _attendance_date: any;
  attendance_date: Date;

  constructor(private attendanceService: AttendanceService,
              private classService: ClassService,
              private datePipe: DatePipe,
              private router: Router) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Attendance', ClientUrl.ATTENDANCE)
    );
  }

  ngOnInit() {
    this.getClassList();
    this.getAttendanceStatusTypes();
  }

  getAttendanceStatusTypes() {
    this.attendanceService.getAttendanceStatusList()
      .subscribe(data => {
          this.attendanceStatusList = data;
        }, error => console.error(error)
      );
  }

  private getClassList() {
    this.classService.getClassDropdown()
      .subscribe(data => {
        this.classList = data;
      }, error => {
        console.log('Error: ', error);
      });
  }

  classSelectEvent(selectedId: any) {
    if (selectedId !== 0) {
      this.classSelectedId = selectedId;
      if (this.attendance_date !== undefined) {
        this.getAttendanceRecords();
      }
    }
  }

  onAttendanceDateChange(result: Date): void {
    this.attendance_date = result;
    if (this.classSelectedId !== 0) {
      this.getAttendanceRecords();
    }
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getAttendanceRecords();
  }

  getAttendanceRecords() {
    this._loading = true;
    this.attendanceService.getAttendanceList(this._currentPage, this.classSelectedId,
      this.datePipe.transform(this.attendance_date, 'dd-MM-yyyy'))
      .subscribe(data => {
        this.attendanceList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this.initCache();
        this._loading = false;
      }, (error: HttpErrorResponse) => {
        console.log('error: ' + error);
      });
  }

  private initCache() {
    this.attendanceList.forEach(item => {
      this.cacheData[item.student_id] = {
        data: {...item}
      };
    });
  }

  onSubmit() {
    this.submitting = true;
    this.attendanceList.forEach(item => {
      const data = this.cacheData[item.student_id].data;
      this.dataSet.push(new AttendanceCreateModel(data.id, data.student_id,
        data.status, data.remarks));
    });
    const request = new AttendanceCreateListModel();
    request.class_id = this.classSelectedId;
    request.attendance_date = this.attendance_date;
    request.data = this.dataSet;

    console.log('after data: ', request);

    this.attendanceService.updateAttendanceRecord(request)
      .subscribe(data => {
        this.router.navigateByUrl(ClientUrl.ATTENDANCE);
      });
    this.submitting = false;
  }
}
