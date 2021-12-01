import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewRegisterOrgComponent } from './view-register-org/view-register-org.component';
import { ViewRegisterUserComponent } from './view-register-user/view-register-user.component';
import { ViewCreateEventComponent } from './view-create-event/view-create-event.component';
import { ViewEventsComponent } from './view-events/view-events.component';
import { ViewLoginComponent } from './view-login/view-login.component';
import { AuthGuardService } from './guards/auth-guard.service';
import { ViewAproveEventComponent } from './view-aprove-event/view-aprove-event.component';
import { ViewRegisterSupplierComponent } from './view-register-supplier/view-register-supplier.component';
import { ViewSpaceControlComponent } from './view-space-control/view-space-control.component';
import { ViewSupplierComponent } from './view-supplier/view-supplier.component';
import { ViewHomeComponent } from './view-home/view-home.component';



const routes: Routes = [
  { path: 'resOrg', component: ViewRegisterOrgComponent,
    canActivate: [AuthGuardService]},
  { path: 'newEvent', component: ViewCreateEventComponent,
    canActivate: [AuthGuardService]},
  { path: 'events', component: ViewEventsComponent},
  { path: 'resUser', component: ViewRegisterUserComponent},
  { path: 'login', component: ViewLoginComponent},
  { path: 'eventSettings', component: ViewAproveEventComponent,
    canActivate: [AuthGuardService]},
  { path: 'resSup', component: ViewRegisterSupplierComponent},
  { path: 'spaceCont', component: ViewSpaceControlComponent},
  { path: 'resSup', component: ViewRegisterSupplierComponent,
    canActivate: [AuthGuardService]},
  { path: 'suppliers', component: ViewSupplierComponent,
    canActivate: [AuthGuardService]},
  { path: 'home', component: ViewHomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
