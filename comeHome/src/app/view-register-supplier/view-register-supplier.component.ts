import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AppService } from '../app.service';

@Component({
  selector: 'app-view-register-supplier',
  templateUrl: './view-register-supplier.component.html',
  styleUrls: ['./view-register-supplier.component.scss']
})
export class ViewRegisterSupplierComponent implements OnInit {
  public formGroup: FormGroup;
  public nomeFornecedor: any;
  public cnpjFornecedor: any;
  public emailFornecedor: any;
  public telefoneFornecedor: any;
  public showSucss: boolean = false;
  public categorias: any
  constructor(public formBuilder: FormBuilder, private appService: AppService,) { }

  ngOnInit(): void {
    this.formGroup = this.createForm();
  }
  createForm(): FormGroup {
    return this.formBuilder.group({
      nomeFornecedor: [this.nomeFornecedor],
      cnpjFornecedor: [this.cnpjFornecedor],
      emailFornecedor: [this.emailFornecedor],
      telefoneFornecedor: [this.telefoneFornecedor],
      categorias: [this.categorias],
    });
  }
  ngSubmit() {
    if (this.formGroup.valid) {
      //trocar pelo serviço de cadastrar fornecedor
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
}
