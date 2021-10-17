import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-view-login',
  templateUrl: './view-login.component.html',
  styleUrls: ['./view-login.component.scss']
})
export class ViewLoginComponent implements OnInit {

  public formGroup: FormGroup;
  @ViewChild('formDirective') 
  private formDirective: NgForm;
  public userValid: boolean = true;
  private email: string;
  private pass: string;
  constructor(private authService: AuthService, public formBuilder: FormBuilder) { }

  createForm(): FormGroup {
    return this.formBuilder.group({
      email: [this.email, Validators.required],
      password: [this.pass, Validators.required],
    });
  }
  ngOnInit(): void {
    this.formGroup = this.createForm();
  }
  ngSubmit() {
    this.authService.userLogin(this.formGroup.value);
    console.log(this.authService.userLogged());
    setTimeout(() =>{this.userValid = this.authService.userLogged()}, 1000);
  }
}
