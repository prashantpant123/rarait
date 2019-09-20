import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';

import {TransportAddComponent} from './transport-add.component';
import {TransportListComponent} from './transport-list.component';
import {TransportRouteAddComponent} from './route/transport-route-add.component';
import {TransportRouteListComponent} from './route/transport-route-list.component';

import {TransportRoutingModule} from './transport-routing.module';
import {SharedModule} from '../../../shared/shared.module';
import {TransportService} from '../core/service/transport.service';
import {TransportRouteService} from '../core/service/transport-route.service';
import {TransportComponent} from './transport.component';
import {TransportRouteComponent} from './route';
import {NgZorroAntdModule} from 'ng-zorro-antd';

@NgModule({
  declarations: [
    TransportListComponent,
    TransportAddComponent,
    TransportComponent,
    TransportRouteListComponent,
    TransportRouteAddComponent,
    TransportRouteComponent
  ],

  imports: [
    CommonModule,
    ReactiveFormsModule,
    TransportRoutingModule,
    NgZorroAntdModule,
    SharedModule
  ],

  providers: [
    TransportService,
    TransportRouteService
  ]
})
export class TransportModule {
}
