import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {NzMessageService} from 'ng-zorro-antd';

import {routerTransition} from '../../../../router.animations';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ClassDropdownModel} from '../../core/model/class/class-dropdown.model';
import {ClassService} from '../../core/service/class.service';
import {ConfirmationDialogSevice} from '../../../../shared/components/confirmation-dailog/confirmation-dialog.sevice';
import {SectionService} from '../../core/service/section.service';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {DisplayListModel} from '../../../../shared/components/confirmation-dailog/display-list.model';
import {SectionCreateModel} from '../../core/model/class/section/section-create.model';

@Component({
  selector: 'section-add',
  templateUrl: './section-add.component.html',
  animations: [routerTransition()]
})
export class SectionAddComponent implements OnInit {

  sectionCreateForm: FormGroup;
  submitted = false;
  route: BreadcrumbModel[] = [];
  classList: ClassDropdownModel[] = [];
  classSelectedId: number = 0;
  classSelectedName: string;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private classService: ClassService,
              private sectionService: SectionService,
              private confirmDialogService: ConfirmationDialogSevice,
              private message: NzMessageService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Class', ClientUrl.CLASS),
      new BreadcrumbModel('Section', ClientUrl.SECTION),
      new BreadcrumbModel('Create', ClientUrl.SECTION_CREATE)
    );
  }

  ngOnInit() {
    this.sectionCreateForm = this.formBuilder.group({
      sectionName: ['', [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(50),
      ]],
    });
    this.getClassDropdown();
  }

  get f() {
    return this.sectionCreateForm.controls;
  }

  classSelectEvent(selectedId: number, selectedName: string) {
    if (selectedId !== 0) {
      this.classSelectedId = selectedId;
      this.classSelectedName = selectedName;
    }
  }

  getClassDropdown() {
    this.classService.getClassDropdown()
      .subscribe(data => {
          this.classList = data;
        }, error => console.error(error)
      );
  }

  onSubmit() {
    this.submitted = true;
    if (this.sectionCreateForm.invalid) {
      return;
    }
    this.confirmDialog();

  }

  private confirmDialog() {
    const displayList: DisplayListModel[] = [];
    displayList.push(new DisplayListModel('Class', this.classSelectedName),
      new DisplayListModel('Section Name', this.sectionCreateForm.value.sectionName));

    this.confirmDialogService.confirm('Confirm', 'Cancel', displayList)
      .then((confirmed) => {
        this.processRequest();
      })
      .catch(() => console.log('User dismissed the dialog'));
  }

  private processRequest() {
    const model = new SectionCreateModel(
      this.classSelectedId,
      this.sectionCreateForm.value.sectionName
    );

    this.sectionService.createNewSession(model)
      .subscribe(data => {
        this.router.navigateByUrl(ClientUrl.SECTION);
        this.message.success('New class section \'<b>' + this.sectionCreateForm.value.sectionName + '</b>\' has been added successfully',
          {nzDuration: 5000});
        this.onCancel();
      });
  }

  onCancel() {
    this.sectionCreateForm.reset();
  }

}
