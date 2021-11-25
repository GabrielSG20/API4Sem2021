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
  public userOrg: boolean = false;
  sideMenu: boolean = false;
  userPermition: boolean = false;
  userAdmin: boolean = false;
  constructor(private authService: AuthService) { }
  ngOnInit(): void {
    this.submenu = false;
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
    } else {
      this.submenu = false;
    }
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
