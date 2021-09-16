import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ViewLoginComponent } from './view-login/view-login.component';
import { ViewRegisterOrgComponent } from './view-register-org/view-register-org.component';

@NgModule({
  declarations: [
    AppComponent,
    ViewLoginComponent,
    ViewRegisterOrgComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
