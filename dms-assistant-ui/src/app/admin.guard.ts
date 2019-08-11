import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';

import {LoginService} from "./shared/services/login/login.service";

@Injectable({
  providedIn: 'root',
})
export class AdminGuard implements CanActivate {
  constructor(private loginService: LoginService, private router: Router) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    return this.isAdmin();
  }

  isAdmin(): boolean {
    if (this.loginService.isAdmin) {
      return true;
    }
    this.router.navigate(['/login']);
  }
}

