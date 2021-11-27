import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AppService } from '../app.service';
import { saveAs } from 'file-saver';

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
  public vaccine: string;
  public oracleEmployee: boolean = false;
  public showSucss: boolean;

  constructor(public formBuilder: FormBuilder, private appService: AppService,) { }

  ngOnInit(): void {
    this.formGroup = this.createForm();
  }
  createForm(): FormGroup {
    return this.formBuilder.group({
      name: [this.name],
      surname: [this.surname],
      nomeCompleto: "",
      tipoUsuario: "",
      cpf: [this.cpf],
      idOracle: [this.oracleId],
      nomeEmpresa: [this.company],
      departamento: [this.sector],
      telefone: [this.phone],
      email: [this.email],
      senhaUsuario: [this.password],
      passConfirm: [this.passConfirm],
      comprovanteVacinacao: [this.vaccine],
    });
  }
  ngSubmit() {
    this.fullName();
    this.functionType();
    console.log(this.formGroup.valid);
    if (this.formGroup.valid) {
      console.log(this.formGroup.value);
        this.appService.insertUser(this.formGroup.value).subscribe(response => {
          },
          error => {
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
  fullName() {
    this.formGroup.patchValue({
      nomeCompleto: `${this.formGroup.value.name} ${this.formGroup.value.surname}`,
    });
  }
  functionType() {
    if (this.formGroup.value.oracleId !== null) {
      this.formGroup.patchValue({
        tipoUsuario: 'interno',
      });
    } else {
      this.formGroup.patchValue({
        tipoUsuario: 'externo',
      });
    }
  }
}

