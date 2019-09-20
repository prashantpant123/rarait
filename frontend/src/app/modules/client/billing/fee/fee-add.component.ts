import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

import {routerTransition} from '../../../../router.animations';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {FeeService} from '../../core/service/billing/fee.service';
import {ConfirmationDialogSevice} from '../../../../shared/components/confirmation-dailog/confirmation-dialog.sevice';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {DisplayListModel} from '../../../../shared/components/confirmation-dailog/display-list.model';
import {FeeCreateModel} from '../../core/model/billing/fee/fee-create.model';
import {ClassDropdownModel} from '../../core/model/class/class-dropdown.model';
import {ClassService} from '../../core/service/class.service';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-fee-add',
  templateUrl: './fee-add.component.html',
  animations: [routerTransition()]
})
export class FeeAddComponent implements OnInit {

  feeCreateForm: FormGroup;
  submitted = false;
  route: BreadcrumbModel[] = [];
  taxValue = true;
  _feeTaxable = 'No';

  clazzList: ClassDropdownModel[];
  classSelectedId: number;
  classSelectedName: string;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private feeService: FeeService,
              private classService: ClassService,
              private confirmDialogService: ConfirmationDialogSevice,
              private message: NzMessageService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Fees', ClientUrl.FEE),
      new BreadcrumbModel('Create', ClientUrl.FEE_CREATE)
    );
  }

  ngOnInit() {
    this.feeCreateForm = this.formBuilder.group({
      feeTitle: ['', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(100)
      ]],
      feeAmount: ['', [
        Validators.required
      ]],
      feeTaxable: ['No', Validators.required],
      feeTaxValue: [''],
      feeDescription: ['']
    });

    this.feeCreateForm.controls['feeTaxable'].valueChanges
      .subscribe(data => {
        if (!data || (typeof data === 'string' && data === 'Yes')) {
          this.feeCreateForm.patchValue({feeTaxValue: 0}, {emitEvent: true});
          this.feeCreateForm.controls['feeTaxValue'].setValidators([Validators.required]);
          this.taxValue = false;
        } else {
          this.feeCreateForm.controls['feeTaxValue'].reset();
          this.feeCreateForm.controls['feeTaxValue'].setValidators([]);
          this.taxValue = true;
        }
      });

    this.getClassList();
  }

  get f() {
    return this.feeCreateForm.controls;
  }

  getClassList() {
    this.classService.getClassDropdown()
      .subscribe(data => {
        this.clazzList = data;
      });
  }

  classSelectEvent(selectedId: any, selectedName: any) {
    if (selectedId !== '0') {
      this.classSelectedId = selectedId;
      this.classSelectedName = selectedName;
    }
  }

  onSubmit() {
    this.submitted = true;
    if (this.feeCreateForm.invalid) {
      return;
    }
    this.confirmDialog();
  }

  private confirmDialog() {
    let displayList: DisplayListModel[] = [];
    displayList.push(
      new DisplayListModel('Class', this.classSelectedName),
      new DisplayListModel('Title', this.feeCreateForm.value.feeTitle),
      new DisplayListModel('Amount(NPR)', this.feeCreateForm.value.feeAmount),
      new DisplayListModel('Taxable', this.feeCreateForm.value.feeTaxable),
      this.taxValue ? new DisplayListModel('', '') : new DisplayListModel('Tax Amount', this.feeCreateForm.value.feeTaxValue),
      new DisplayListModel('Description', this.feeCreateForm.value.feeDescription)
    );

    this.confirmDialogService.confirm('Confirm', 'Cancel', displayList)
      .then((confirmed) => {
        this.processRequest();
      })
      .catch(() => console.log('User dismissed the dialog'));
  }

  private processRequest() {
    const id = this.message.loading('Processing request, Please wait ...', {nzDuration: 0}).messageId;
    const model = new FeeCreateModel(
      this.classSelectedId,
      this.feeCreateForm.value.feeTitle,
      this.feeCreateForm.value.feeAmount,
      this.feeCreateForm.value.feeTaxable === 'Yes' ? true : false,
      this.feeCreateForm.value.feeTaxable === 'Yes' ? this.feeCreateForm.value.feeTaxValue : 0,
      this.feeCreateForm.value.feeDescription
    );

    this.feeService.addFee(model)
      .subscribe(data => {
          this.router.navigateByUrl(ClientUrl.FEE);
          this.message.remove(id);
          this.message.success('New fee \'<b>' + this.feeCreateForm.value.feeTitle + '</b>\' has been added successfully',
            {nzDuration: 5000});
          this.onCancel();
        },
        error => {
          this.message.remove(id);
        });
  }

  onCancel() {
    this.feeCreateForm.reset();
  }

}
