import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewRegisterOrgComponent } from './view-register-org/view-register-org.component';
import { ViewCreateEventComponent } from './view-create-event/view-create-event.component';
import { ViewEventsComponent } from './view-events/view-events.component';

const routes: Routes = [
  { path: 'resOrg', component: ViewRegisterOrgComponent },
  { path: 'newEvent', component: ViewCreateEventComponent},
  { path: 'events', component: ViewEventsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
