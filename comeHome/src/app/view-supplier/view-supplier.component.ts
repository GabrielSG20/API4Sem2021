import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-supplier',
  templateUrl: './view-supplier.component.html',
  styleUrls: ['./view-supplier.component.scss']
})
export class ViewSupplierComponent implements OnInit {
  public categories: string[];
  constructor() { }

  ngOnInit(): void {
    this.categories= ["Serviços Gerais","Alimentação","Equipamentos de Som","Imagem e Gravação","Outros"]     
  }

}
