import {
  Component,
  OnInit
} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {routerTransition} from '../../../../router.animations';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ClassService} from '../../core/service/class.service';
import {SectionService} from '../../core/service/section.service';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {SectionListModel} from '../../core/model/class/section/section-list.model';
import {ClassDropdownModel} from '../../core/model/class/class-dropdown.model';
import {SubjectListModel} from '../../core/model/subject/subject-list.model';

@Component({
  selector: 'class-routine-list',
  templateUrl: './class-routine-list.component.html',
  animations: [routerTransition()]
})
export class ClassRoutineListComponent implements OnInit {

  classList: ClassDropdownModel[] = [];
  route: BreadcrumbModel[] = [];
  sectionList: SectionListModel[] = [];
  subjectList: SubjectListModel[];

  _loading = false;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  classSelectedId: number = 0;
  routeLink$ = '/';
  text$ = 'New Routine';
  classId = '0';

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private classService: ClassService,
              private sectionService: SectionService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Section', ClientUrl.SECTION)
    );
  }

  ngOnInit() {
    this.getClassDropdown();
  }

  classSelectEvent(selectedId: number) {
    if (selectedId !== 0) {
      this.classSelectedId = selectedId;
    }
  }

  getClassDropdown() {
    this.classService.getClassDropdown()
      .subscribe(data => {
          this.classList = data;
        }, error => console.error(error)
      );
  }

  onPageChange(page: number) {
    this._currentPage = page;
    this.getSectionList();
  }

  private getSectionList() {
    this._loading = true;
    this.sectionService.getSectionList(this._currentPage, this.classSelectedId)
      .subscribe(data => {
        this.sectionList = data.content;
        this._totalItem = data.total_data;
        this._currentPage = data.current_page;
        this._loading = false;
      });
  }

}
