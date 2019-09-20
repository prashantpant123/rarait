import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {NotFoundComponent} from './modules/static/not-found/not-found.component';
import {AuthRoleGuard} from './shared/guard';
import {Role} from './shared/model/role';

const routes: Routes = [
  // {
  //   path: '',
  //   loadChildren: './modules/static/landing/landing-page.module#LandingPageModule'
  // },
  {
    path: 'admin',
    loadChildren: './modules/admin/admin.module#AdminModule',
    // canActivate: [AuthRoleGuard],
    // canActivateChild: [AuthRoleGuard]
  },
  {
    path: 'client',
    loadChildren: './modules/client/client.module#ClientModule',
    // canActivate: [AuthRoleGuard],
    // canActivateChild: [AuthRoleGuard]
  },
  {
    path: 'student',
    loadChildren: './modules/student/student.module#StudentModule'
  },
  {
    path: 'not-found',
    component: NotFoundComponent
  },
  // {path: '**', redirectTo: 'not-found'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
