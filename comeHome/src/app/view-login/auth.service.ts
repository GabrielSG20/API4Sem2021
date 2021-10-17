import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  url = 'http://localhost:8081';
  private userAuth: boolean = false;
  private userPermission: boolean = false;
  private userData: any;
  menuTop = new EventEmitter<boolean>();
  menuSide = new EventEmitter<boolean>();
  userName = new EventEmitter<string>();
  userEmail = new EventEmitter<string>();
  constructor(private router: Router, 
    private httpClient: HttpClient,) { }
    
  protected head = new HttpHeaders({
    Accept: 'application/json',
  });
  
  protected handleError(error: any): Observable<any> {
    return throwError(error);
  }
  
  userLogin(user: any) {
    this.httpClient.get<any>(`${this.url}/usuarios/${user.email}`, {headers: this.head})
    .subscribe((values) => {
      this.userData = values;
      if (user.password === this.userData.senhaUsuario) {
        if (this.userData.tipoUsuario === 'interno'){
          this.userPermission = true;
        }
        this.userAuth = true;
        this.menuSide.emit(this.userPermission);
        this.userName.emit(this.userData.nomeCompleto);
        this.userEmail.emit(this.userData.email);
        this.router.navigate(['/']);
      } else {
        this.userAuth = false;
        this.userPermission = false;
      }
    });
  }
  userLogout() {
    this.userPermission = false
    this.userAuth = false;
    this.menuSide.emit(this.userPermission);
    this.userName.emit('');
    this.userEmail.emit('');
    this.router.navigate(['/login']);
  }
  userLogged() {
    return this.userAuth;
  }
  userOracle() {
    return this.userPermission;
  }
}
