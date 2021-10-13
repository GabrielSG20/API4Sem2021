import { EventEmitter, Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userAuth: boolean = false;
  private userPermission: boolean = false;
  menuTop = new EventEmitter<boolean>();
  menuSide = new EventEmitter<boolean>();
  userName = new EventEmitter<string>();
  constructor(private router: Router) { }

  userLogin(user: any) {
    if (user.email === 'mike-msn@hotmail.com' && user.password === '123' ) {
      this.userAuth = true;
      this.userPermission = true;
      this.menuSide.emit(this.userPermission);
      this.userName.emit('Mike');
      this.router.navigate(['/']);
    }
    else if (user.email === 'gabriel_4.1@hotmail.com.br' && user.password === '123') {
      this.userAuth = true;
      this.userPermission = false;
      this.userName.emit('Gabriel');
      this.menuSide.emit(this.userPermission);
      this.router.navigate(['/']);
    }
    else {
      this.userAuth = false;
      this.userPermission = false;
    }
  }
  userLogged() {
    return this.userAuth;
  }
  userOracle() {
    return this.userPermission;
  }
}
