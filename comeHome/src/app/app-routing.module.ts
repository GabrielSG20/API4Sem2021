import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewRegisterOrgComponent } from './view-register-org/view-register-org.component';

const routes: Routes = [
  { path: 'resOrg', component: ViewRegisterOrgComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
