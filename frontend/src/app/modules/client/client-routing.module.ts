import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ClientLayoutComponent} from './client-layout.component';

const routes: Routes = [
  {
    path: '',
    component: ClientLayoutComponent,
    children: [
      {
        path: '',
        redirectTo: 'dashboard', pathMatch: 'full'
      },
      {
        path: 'dashboard',
        loadChildren: './dashboard/dashboard.module#DashboardModule'
      },
      {
        path: 'student',
        loadChildren: './student/student.module#StudentModule'
      },
      {
        path: 'exam',
        loadChildren: './exam/exam.module#ExamModule'
      },
      {
        path: 'transport',
        loadChildren: './transport/transport.module#TransportModule'
      },
      {
        path: 'academic-session',
        loadChildren: './academic-session/academic-session.module#AcademicSessionModule'
      },
      {
        path: 'subject',
        loadChildren: './subject/subject.module#SubjectModule'
      },
      {
        path: 'staff',
        loadChildren: './staff/staff.module#StaffModule'
      },
      {
        path: 'bill',
        loadChildren: './billing/billing.module#BillingModule'
      },
      {
        path: 'class',
        loadChildren: './class/class.module#ClassModule'
      },
      {
        path: 'attendance',
        loadChildren: './attendance/attendance.module#AttendanceModule'
      }

      // { path: 'charts', loadChildren: './charts/charts.module#ChartsModule' },
      // { path: 'tables', loadChildren: './tables/tables.module#TablesModule' },
      // { path: 'forms', loadChildren: './form/form.module#FormModule' },
      // { path: 'bs-element', loadChildren: './bs-element/bs-element.module#BsElementModule' },
      // { path: 'grid', loadChildren: './grid/grid.module#GridModule' },
      // { path: 'components', loadChildren: './bs-component/bs-component.module#BsComponentModule' },
      // { path: 'blank-page', loadChildren: './blank-page/blank-page.module#BlankPageModule' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule {
}
