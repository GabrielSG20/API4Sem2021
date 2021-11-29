import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { AuthService } from 'src/app/view-login/auth.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss']
})
export class SideMenuComponent implements OnInit {
  public submenu: boolean;
  public subReports: boolean;
  public subFornecedores: boolean;
  public userName: string;
  public userOrg: boolean = false;
  sideMenu: boolean = false;
  userPermition: boolean = false;
  userAdmin: boolean = false;
  constructor(private authService: AuthService, private appService: AppService ) { }
  ngOnInit(): void {
    this.submenu = false;
    this.subReports = false;
    this.authService.menuSide.subscribe((values: boolean) => {
      this.sideMenu = values;
    });
    this.authService.userName.subscribe((values: string) => {
      this.userName = values;
    });
    this.userType();
  }
  openSub() {
    if(this.submenu == false) {
      this.submenu = true;
      this.subReports = false;
      this.subFornecedores = false
    } else {
      this.submenu = false;
    }
  }
  openReports() {
    if(this.subReports == false) {
      this.subReports = true;
      this.submenu = false;
      this.subFornecedores = false
    } else {
      this.subReports = false;
    }
  }
  openFornecedores() {
    if(this.subFornecedores == false) {
      this.subFornecedores = true;
      this.submenu = false;
      this.subReports = false;
    } else {
      this.subReports = false;
    }
  }
  downloadFileEventoAberto() {
    const year = new Date();
    const month = new Date();
    this.appService.getDownloadEventoAberto().subscribe(blob => saveAs(blob, `relatorio_eventos_abertos_${month.getMonth()+1}-${year.getFullYear()}`));
  }
  downloadFileEventoFechado() {
    const year = new Date();
    const month = new Date();
    this.appService.getDownloadEventoFechado().subscribe(blob => saveAs(blob, `relatorio_eventos_fechados_${month.getMonth()+1}-${year.getFullYear()}`));
  }
  downloadFileConvidadoPublico() {
    const year = new Date();
    const month = new Date();
    this.appService.getDownloadConvidadosAberto().subscribe(blob => saveAs(blob, `relatorio_convidados_publicos_${month.getMonth()+1}-${year.getFullYear()}`));
  }
  downloadFileConvidadoPrivado() {
    const year = new Date();
    const month = new Date();
    this.appService.getDownloadConvidadosFechado().subscribe(blob => saveAs(blob, `relatorio_convidados_privados_${month.getMonth()+1}-${year.getFullYear()}`));
  }

  userType(){
    this.authService.userType.subscribe((values: string) => {
      if (values === 'org') {
        this.userPermition = true;
        this.userOrg = true;
      } else if (values === 'admin') {
        this.userAdmin = true;
      } else if (values === 'interno') {
        this.userPermition = true;
        this.userOrg = false;
      } else {
        this.userAdmin = false;
        this.userPermition = false;
        this.userOrg = false;
      }
    })
  }
}

