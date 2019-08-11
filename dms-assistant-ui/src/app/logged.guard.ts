import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';

import {LoginService} from './shared/services/login/login.service';

@Injectable({
  providedIn: 'root',
})
export class LoggedGuard implements CanActivate {
  constructor(private loginService: LoginService, private router: Router) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    return this.isLoggedIn();
  }

  isLoggedIn(): boolean {
    if (this.loginService.isAdmin) {
      this.router.navigate(['/admin']);
    }

    if (this.loginService.isLoggedIn) {
      this.router.navigate(['/survey']);
    }
    return true;
  }
}
