import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/view-login/auth.service';

@Component({
  selector: 'app-menu-top',
  templateUrl: './menu-top.component.html',
  styleUrls: ['./menu-top.component.scss']
})
export class MenuTopComponent implements OnInit {
  public userName: String;
  public logged: Boolean;
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.userName.subscribe((values: string) => {
      this.userName = values;
    });
    this.authService.userSucess.subscribe((values: boolean) => {
      this.logged = values;
    });
    console.log(this.logged);
  }
  userLogout() {
    this.authService.userLogout();
  }

}
