import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

import { AppService } from '../app.service';
import { AuthService } from '../view-login/auth.service';

@Component({
  selector: 'app-view-register-org',
  templateUrl: './view-register-org.component.html',
  styleUrls: ['./view-register-org.component.scss']
})
export class ViewRegisterOrgComponent implements OnInit {
  @ViewChild('formDirective') 
  private formDirective: NgForm;
  public formGroup: FormGroup;
  public userFunction: any;
  public showSucss: boolean;
  public orgEmail: string;
  constructor(
    public formBuilder: FormBuilder,
    private appService: AppService,
    private authService: AuthService,
    ) { }

  ngOnInit(): void {
    this.formGroup = this.createForm();
  }

  createForm(): FormGroup {
    return this.formBuilder.group({
      cargoUsuario: [this.userFunction, Validators.required],
      email: this.orgEmail,
    });
  }

  ngSubmit() {
    this.userEmail();
    this.appService.updateOrg(this.formGroup.value).subscribe((res) => {
      this.formDirective.resetForm();
      this.formGroup.reset();
      this.showSucss = true;
      setTimeout(() =>{this.showSucss = false;}, 4000);
    });
  }

  userEmail(){
    this.orgEmail = this.authService.getEmail();
    this.formGroup.patchValue({
      email: this.orgEmail,
    });
  }
}
