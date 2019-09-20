import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {routerTransition} from '../../../../router.animations';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ClassService} from '../../core/service/class.service';
import {SectionService} from '../../core/service/section.service';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {SectionListModel} from '../../core/model/class/section/section-list.model';
import {ClassDropdownModel} from '../../core/model/class/class-dropdown.model';


@Component({
  selector: 'class-section-list',
  templateUrl: './section-list.component.html',
  animations: [routerTransition()]
})
export class SectionListComponent implements OnInit {

  classList: ClassDropdownModel[] = [];
  route: BreadcrumbModel[] = [];
  sectionList: SectionListModel[] = [];

  _loading = false;
  _pagination = true;
  _noData = 'No data found!';
  _totalItem = 0;
  _currentPage: number = 1;

  text$ = 'New Section';
  routeLink$ = '/client/class/section/create';
  classSelectedId: number = 0;
  classId = '0';

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private classService: ClassService,
              private sectionService: SectionService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Class', ClientUrl.CLASS),
      new BreadcrumbModel('Section', ClientUrl.SECTION)
    );
  }

  ngOnInit() {
    this.getClassDropdown();
    this.getSectionList();
  }

  getClassDropdown() {
    this.classService.getClassDropdown()
      .subscribe(data => {
          this.classList = data;
        }, error => console.error(error)
      );
  }

  classSelectEvent(selectedId: number) {
    if (selectedId !== 0) {
      this.classSelectedId = selectedId;
      this.getSectionList();
    }
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

  onPageChange(page: number) {
    this._currentPage = page;
    this.getSectionList();
  }
}
