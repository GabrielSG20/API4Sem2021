import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/view-login/auth.service';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss']
})
export class SideMenuComponent implements OnInit {
  public submenu: boolean;
  public userName: string;
  sideMenu: boolean = false;
  constructor(private authService: AuthService) { }
  ngOnInit(): void {
    this.submenu = false;
    this.authService.menuSide.subscribe((values: boolean) => {
      this.sideMenu = values;
    });
    this.authService.userName.subscribe((values: string) => {
      this.userName = values;
    });
  }
  openSub() {
    if(this.submenu == false) {
      this.submenu = true;
    } else {
      this.submenu = false;
    }
  }
}
