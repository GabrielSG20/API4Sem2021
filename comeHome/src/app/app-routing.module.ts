import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewRegisterOrgComponent } from './view-register-org/view-register-org.component';
import { ViewRegisterUserComponent } from './view-register-user/view-register-user.component';

const routes: Routes = [
  { path: 'resOrg', component: ViewRegisterOrgComponent },
  { path: 'resUser', component: ViewRegisterUserComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
