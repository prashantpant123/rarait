import {PrintLayoutComponent} from "./print-layout.component";
import {RouterModule, Routes} from "@angular/router";
import {StudentProfileTemplateComponent} from "./student-profile-template.component";
import {NgModule} from "@angular/core";

const routes: Routes = [
  { path: 'print',
    outlet: 'print',
    component: PrintLayoutComponent,
    children: [
      { path: 'student-profile-template',
        component: StudentProfileTemplateComponent},
    ]
  },
  {
    path:'student-profile-template',
    component: StudentProfileTemplateComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrintRoutingModule { }
export const routingComponents= [StudentProfileTemplateComponent]
