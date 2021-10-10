import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss']
})
export class SideMenuComponent implements OnInit {
  public submenu: boolean;

  ngOnInit(): void {
    this.submenu = false;
  }
  openSub() {
    if(this.submenu == false) {
      this.submenu = true;
    } else {
      this.submenu = false;
    }
  }
}
