import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { ReactiveFormsModule, FormsModule} from '@angular/forms';
import { FlatpickrModule } from 'angularx-flatpickr';
import { registerLocaleData, CommonModule } from '@angular/common';
import localeBr from '@angular/common/locales/pt';
registerLocaleData(localeBr);

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button'
import {MatIconModule} from '@angular/material/icon'
import {MatSidenavModule} from '@angular/material/sidenav'
import {MatListModule} from '@angular/material/list'
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatRadioModule} from '@angular/material/radio';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatDialogModule} from '@angular/material/dialog';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { MenuTopComponent } from './shared/menu-top/menu-top.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SideMenuComponent } from './shared/side-menu/side-menu.component';
import { ViewLoginComponent } from './view-login/view-login.component';
import { ViewRegisterOrgComponent } from './view-register-org/view-register-org.component';
import { ViewRegisterUserComponent } from './view-register-user/view-register-user.component';
import { ViewCreateEventComponent } from './view-create-event/view-create-event.component';
import AppMockedService from './app.mocked.service';
import { ViewEventsComponent } from './view-events/view-events.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from './view-login/auth.service';
import { ViewAproveEventComponent } from './view-aprove-event/view-aprove-event.component';
import { DialogAproveEventComponent } from './view-aprove-event/dialog-aprove-event/dialog-aprove-event.component';
import { DialogInfoEventComponent } from './view-events/dialog-info-event/dialog-info-event.component';
import { ViewRegisterSupplierComponent } from './view-register-supplier/view-register-supplier.component';
import { ViewSpaceControlComponent } from './view-space-control/view-space-control.component';


@NgModule({
  declarations: [
    AppComponent,
    MenuTopComponent,
    SideMenuComponent,
    ViewLoginComponent,
    ViewRegisterOrgComponent,
    ViewRegisterUserComponent,
    ViewCreateEventComponent,
    ViewEventsComponent,
    ViewAproveEventComponent,
    DialogAproveEventComponent,
    DialogInfoEventComponent,
    ViewRegisterSupplierComponent,
    ViewSpaceControlComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatRadioModule,
    MatDialogModule,
    MatCheckboxModule,
    HttpClientModule,
    AppRoutingModule,
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory },),
    CommonModule,
    FormsModule,
    NgbModalModule,
    FlatpickrModule.forRoot(),
  ],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'pt-BR'},
    { provide: LOCALE_ID, useValue: 'pt'},
    AppMockedService,
    AuthService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
