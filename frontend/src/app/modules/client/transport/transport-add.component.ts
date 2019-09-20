import {Router} from '@angular/router';
import {
  Component,
  OnInit
} from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import {NzMessageService} from 'ng-zorro-antd';

import {routerTransition} from '../../../router.animations';
import {BreadcrumbModel} from '../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../shared/constants/client-url';
import {DisplayListModel} from '../../../shared/components/confirmation-dailog/display-list.model';

import {ConfirmationDialogSevice} from '../../../shared/components/confirmation-dailog/confirmation-dialog.sevice';
import {TransportService} from '../core/service/transport.service';
import {TransportCreateModel} from '../core/model/transport/transport-create.model';

@Component({
  selector: 'app-transport-add',
  templateUrl: './transport-add.component.html',
  animations: [routerTransition()]
})
export class TransportAddComponent implements OnInit {

  transportRegisterForm: FormGroup;
  submitted = false;
  route: BreadcrumbModel[] = [];

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private transportService: TransportService,
              private confirmDialogService: ConfirmationDialogSevice,
              private message: NzMessageService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Transport', ClientUrl.TRANSPORT),
      new BreadcrumbModel('Create', ClientUrl.TRANSPORT + '/create')
    );
  }

  ngOnInit() {
    this.transportRegisterForm = this.formBuilder.group({
      vehicleName: ['', null],
      vehicleNumber: ['', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50)
      ]],
    });
  }

  get f() {
    return this.transportRegisterForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.transportRegisterForm.invalid) {
      return;
    }
    this.confirmDialog();
  }

  public confirmDialog() {
    const displayList: DisplayListModel[] = [];
    displayList.push(
      new DisplayListModel('Transport Name: ', this.transportRegisterForm.value.vehicleName ?
        this.transportRegisterForm.value.vehicleName : 'N/A'),
      new DisplayListModel('Transport Number Plate', this.transportRegisterForm.value.vehicleNumber)
    );

    this.confirmDialogService.confirm('Confirm', 'Cancel', displayList)
      .then((confirmed) => {
        // TODO: show processing until request to server side is completed or timeout of 20s
        this.processRequest();
      })
      .catch(() => console.log('User dismissed the dialog'));
  }

  private processRequest() {
    const tm = new TransportCreateModel(
      this.transportRegisterForm.value.vehicleName,
      this.transportRegisterForm.value.vehicleNumber
    );

    this.transportService.createNewTransport(tm)
      .subscribe(data => {
        this.router.navigateByUrl(ClientUrl.TRANSPORT);
        this.message.success('New transport \'' + this.transportRegisterForm.value.vehicleName + '\' has been added successfully',
          {nzDuration: 5000});
        this.onCancel();
      });
  }

  onCancel() {
    this.transportRegisterForm.reset();
  }
}
