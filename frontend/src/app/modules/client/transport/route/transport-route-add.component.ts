import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';

import {routerTransition} from '../../../../router.animations';
import {BreadcrumbModel} from '../../../../shared/components/breadcrumb/breadcrumb.model';
import {ClientUrl} from '../../../../shared/constants/client-url';
import {TransportDropdownModel} from '../../core/model/transport/transport-dropdown.model';
import {TransportService} from '../../core/service/transport.service';
import {TransportRouteService} from '../../core/service/transport-route.service';
import {ConfirmationDialogSevice} from '../../../../shared/components/confirmation-dailog/confirmation-dialog.sevice';
import {DisplayListModel} from '../../../../shared/components/confirmation-dailog/display-list.model';
import {TransportRouteCreateModel} from '../../core/model/transport/transport-route-create.model';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-transport-route-add',
  templateUrl: './transport-route-add.component.html',
  styleUrls: ['../datepicker.css'],
  animations: [routerTransition()]
})
export class TransportRouteAddComponent implements OnInit {
  routeRegistrationForm: FormGroup;
  submitted = false;
  route: BreadcrumbModel[] = [];
  transports: TransportDropdownModel[];

  transportSelectId: number = 0;
  transportSelectName: string;

  pickupTime: Date | null = null;
  dropTime: Date | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private transportService: TransportService,
    private transportRouteService: TransportRouteService,
    private confirmDialogService: ConfirmationDialogSevice,
    private message: NzMessageService) {
    this.route.push(
      new BreadcrumbModel('Dashboard', ClientUrl.DASHBOARD),
      new BreadcrumbModel('Transport Route', ClientUrl.TRANSPORT_ROUTE),
      new BreadcrumbModel('Create', ClientUrl.TRANSPORT_ROUTE + '/create')
    );
  }

  ngOnInit() {
    this.routeRegistrationForm = this.formBuilder.group({
      busId: [this.transportSelectName,
        Validators.required
      ],
      busRoutePath: ['', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(250)
      ]],
      pickupTime: [this.pickupTime, null],
      dropTime: [this.dropTime, null]
    });

    this.getTransportDropdownList();
  }

  get f() {
    return this.routeRegistrationForm.controls;
  }

  getTransportDropdownList() {
    this.transportService.getTransportDropdown()
      .subscribe(data => {
        this.transports = data;
      }, error => console.log('Error ', error));
  }

  transportSelectEvent(selectedId: any, selectedName: any) {
    if (selectedId !== '0') {
      this.transportSelectId = selectedId;
      this.transportSelectName = selectedName;
    }
  }

  onSubmit() {
    this.submitted = true;
    if (this.routeRegistrationForm.invalid) {
      return;
    }
    this.confirmDialog();
  }

  private confirmDialog() {
    const displayList: DisplayListModel[] = [];
    displayList.push(
      new DisplayListModel('Transport Name: ', this.transportSelectName ?
        this.transportSelectName : 'N/A'),
      new DisplayListModel('Route Path', this.routeRegistrationForm.value.busRoutePath),
      new DisplayListModel('Pickup Time', (this.pickupTime.getHours() < 10 ? '0' + this.pickupTime.getHours() : this.pickupTime.getHours())
        + ':' + (this.pickupTime.getMinutes() < 10 ? '0' + this.pickupTime.getMinutes() : this.pickupTime.getMinutes())),
      new DisplayListModel('Drop Time', (this.dropTime.getHours() < 10 ? '0' + this.dropTime.getHours() : this.dropTime.getHours())
        + ':' + (this.dropTime.getMinutes() < 10 ? '0' + this.dropTime.getMinutes() : this.dropTime.getMinutes())),
    );

    this.confirmDialogService.confirm('Confirm', 'Cancel', displayList)
      .then((confirmed) => {
        this.processRequest();
      })
      .catch(() => console.log('User dismissed the dialog'));
  }

  private processRequest() {
    const model = new TransportRouteCreateModel(
      this.transportSelectId,
      this.routeRegistrationForm.value.busRoutePath,
      (this.pickupTime.getHours() < 10 ? '0' + this.pickupTime.getHours() : this.pickupTime.getHours()) + ':' +
      (this.pickupTime.getMinutes() < 10 ? '0' + this.pickupTime.getMinutes() : this.pickupTime.getMinutes()),
      (this.dropTime.getHours() < 10 ? '0' + this.dropTime.getHours() : this.dropTime.getHours()) + ':' +
      (this.dropTime.getMinutes() < 10 ? '0' + this.dropTime.getMinutes() : this.dropTime.getMinutes())
    );

    this.transportRouteService.newTransportRoute(model)
      .subscribe(data => {
        this.router.navigateByUrl(ClientUrl.TRANSPORT_ROUTE);
        this.message.success('New transport route \'' + this.routeRegistrationForm.value.vehicleName + '\' has been added successfully',
          {nzDuration: 5000});
        this.onCancel();
      }, error => {
        console.log('Error ', error);
      });
  }

  onCancel() {
    this.routeRegistrationForm.reset();
  }

}
