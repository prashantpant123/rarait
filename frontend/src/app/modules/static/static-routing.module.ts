import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {ServerErrorComponent} from './server-error/server-error.component';
import {AccessDeniedComponent} from './access-denied/access-denied.component';
import {StaticComponent} from './static.component';

const routes: Routes = [
  {
    path: '',
    component: StaticComponent
  },
  {
    path: 'error',
    component: ServerErrorComponent
  },
  {
    path: 'access-denied',
    component: AccessDeniedComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StaticRoutingModule {
}
