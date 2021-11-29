import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AppService } from '../app.service';

@Component({
  selector: 'app-view-supplier',
  templateUrl: './view-supplier.component.html',
  styleUrls: ['./view-supplier.component.scss']
})
export class ViewSupplierComponent implements OnInit {
  public categories: string[];
  public data: any;
  public formGroup: FormGroup;
  public category: any;
  constructor(
    public formBuilder: FormBuilder,
    private appService: AppService,) { }

  ngOnInit(): void {
    this.formGroup = this.createForm();
    this.categories= ["Serviços Gerais","Alimentação","Equipamentos de Som","Imagem e Gravação","Outros"]   
    this.data = [{
      "cnpj":"999999999999",
      "nomeFornecedor":"ramonzinbeats",
      "telefoneFornecedor":129820506054,
      "emailFornecedor":"Ramon",
      "categorias":[
          {
          "idCategoria":1,
          "nomeCategoria":"Outros",
          "descCategoria":"TESTE1"
          }]
      },
      {
        "cnpj":"11111111111111",
        "nomeFornecedor":"ramonzinbeats",
        "telefoneFornecedor":129820506054,
        "emailFornecedor":"Ramon",
        "categorias":[
            {
            "idCategoria":1,
            "nomeCategoria":"Alimentação",
            "descCategoria":"TESTE1"
            }]
        },
    ]
  }
  createForm(): FormGroup {
    return this.formBuilder.group({
      nomeCategoria: [this.category],
    });
  }
  searchSupplier() {
    console.log(this.formGroup.value.nomeCategoria);
    let filter = []
    for (var element of this.data) {
     if (element.categorias[0].nomeCategoria == this.formGroup.value.nomeCategoria) {
       filter.push(element);
     } 
    }
    this.data = filter;
  }
  getSuppliers() {
    this.appService.getApprovedEvents().subscribe((values) => {
      this.data = values;
    });
  }
}
