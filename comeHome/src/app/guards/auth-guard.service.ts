import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../view-login/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private authService: AuthService, private router: Router) { }
  canActivate(
  route: ActivatedRouteSnapshot, 
  state: RouterStateSnapshot): Observable<boolean> | boolean {
    if (this.authService.userLogged()) {
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }
}
