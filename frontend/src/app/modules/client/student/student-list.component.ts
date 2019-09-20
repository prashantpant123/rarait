import {Component, OnInit} from '@angular/core';

import {routerTransition} from '../../../router.animations';
import {StudentService} from '../core/service/student.service';
import {ClientUrl} from '../../../shared/constants/client-url';
import {StudentListModel} from '../core/model/student/student-list.model';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {ClassService} from '../core/service/class.service';
import {ClassDropdownModel} from '../core/model/class/class-dropdown.model';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  animations: [routerTransition()],
  styles: [`
    .search-box {
      padding: 8px;
    }

    .search-box input {
      width: 188px;
      margin-bottom: 8px;
      display: block;
    }

    .search-box button {
      width: 90px;
    }

    .search-button {
      margin-right: 8px;
    }
  `]
})
export class StudentListComponent implements OnInit {

  studentList: StudentListModel[] = [];
  classList: ClassDropdownModel[] = [];
  route: BreadcrumbModel[] = [];

  _loading = true;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  classValue = '0';
  classSelectedId: number = 0;
  text$ = 'New Student';
  routeLink$ = '/client/student/create';
  excelRouteLink$ = '/client/student/excel';

  searchValue = '';
  searchRegistrationValue = '';
  sortValue: string | null = null;
  sortKey: string | null = null;

  constructor(private studentService: StudentService,
              private classService: ClassService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Students', ClientUrl.STUDENT)
    );
  }

  ngOnInit() {
    this.getClassList();
    this.getStudentList();
  }

  private getClassList() {
    this.classService.getClassDropdown()
      .subscribe(data => {
        this.classList = data;
      }, error => {
        console.log('Error: ', error);
      });
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getStudentList();
  }

  search(): void {
    this._loading = true;
    this.studentService.searchStudent(this._currentPage, this.classSelectedId, 'full_name', this.searchValue)
      .subscribe(data => {
        this.studentList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this._loading = false;
      });
  }

  reset(): void {
    this.searchValue = '';
    this.getStudentList();
  }

  searchRegistrationNo(): void {
    this._loading = true;
    this.studentService.searchStudent(this._currentPage, this.classSelectedId, 'registration_id', this.searchRegistrationValue)
      .subscribe(data => {
          this.studentList = data.content;
          this._totalItem = data.total_data;
          this._currentPage = data.current_page;
          this._loading = false;
        }, error => console.log('Error ', error.toString())
      );
  }

  sort(sort: { key: string; value: string }): void {
    this.sortKey = sort.key;
    this.sortValue = sort.value;
    this.getStudentList();
  }

  private getStudentList() {
    this._loading = true;
    this.studentService.getStudentList(this._currentPage, this.classSelectedId, this.sortKey, this.sortValue)
      .subscribe(data => {
        this.studentList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this._loading = false;
      });
  }

  classSelectEvent(selectedId: any) {
    if (selectedId !== 0){
      this.classSelectedId = selectedId;
      this.getStudentList();
    }
  }

}
