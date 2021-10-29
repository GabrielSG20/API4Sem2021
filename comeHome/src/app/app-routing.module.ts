import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewRegisterOrgComponent } from './view-register-org/view-register-org.component';
import { ViewRegisterUserComponent } from './view-register-user/view-register-user.component';
import { ViewCreateEventComponent } from './view-create-event/view-create-event.component';
import { ViewEventsComponent } from './view-events/view-events.component';
import { ViewLoginComponent } from './view-login/view-login.component';
import { AuthGuardService } from './guards/auth-guard.service';
import { ViewAproveEventComponent } from './view-aprove-event/view-aprove-event.component';

const routes: Routes = [
  { path: 'resOrg', component: ViewRegisterOrgComponent,
    },
  { path: 'newEvent', component: ViewCreateEventComponent,
    canActivate: [AuthGuardService]},
  { path: 'events', component: ViewEventsComponent},
  { path: 'resUser', component: ViewRegisterUserComponent},
  { path: 'login', component: ViewLoginComponent},
  { path: 'eventSettings', component: ViewAproveEventComponent,
    canActivate: [AuthGuardService]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
