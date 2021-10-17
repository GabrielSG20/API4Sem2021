import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AppService } from '../app.service';

@Component({
  selector: 'app-view-register-user',
  templateUrl: './view-register-user.component.html',
  styleUrls: ['./view-register-user.component.scss']
})
export class ViewRegisterUserComponent implements OnInit {
  public formGroup: FormGroup;
  public name: string;
  public surname: string;
  public cpf: string;
  public company: string;
  public oracleId: string;
  public sector: string;
  public phone: string;
  public email: string;
  public password: string;
  public passConfirm: string;
  public oracleEmployee: boolean = false;
  public showSucss: boolean;

  constructor(public formBuilder: FormBuilder, private appService: AppService,) { }

  ngOnInit(): void {
    this.formGroup = this.createForm();
  }
  createForm(): FormGroup {
    return this.formBuilder.group({
      name: [this.name, Validators.required],
      surname: [this.surname, Validators.required],
      cpf: [this.cpf, Validators.required],
      oracleId: [this.oracleId, Validators.required],
      company: [this.company, Validators.required],
      sector: [this.sector, Validators.required],
      phone: [this.phone, Validators.required],
      email: [this.email, Validators.required],
      password: [this.password, Validators.required],
      passConfirm: [this.passConfirm, Validators.required],

    });
  }
  ngSubmit() {
    if (this.formGroup.valid) {
        this.appService.insertResult(this.formGroup.value).subscribe(response => {
          },
          () => {
            this.showSucss = true;
            setTimeout(() =>{this.showSucss = false;}, 4000);
          });
    }
    this.formGroup.reset();
  }
  userCompany() {
    this.oracleEmployee = false;
  }
  userOracle() {
    this.oracleEmployee = true;
  }
}

