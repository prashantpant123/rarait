import {Injectable} from '@angular/core';
import {Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivateChild, UrlTree} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';
import {Role} from '../model/role';

@Injectable({providedIn: 'root'})
export class AuthRoleGuard implements CanActivate {

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    if (!this.authService.currentToken()) {
      this.router.navigateByUrl('/login');
      return false;
    }

    let user = this.authService.currentUserValue();
    if (user.role === Role.ADMIN) {
      this.router.navigateByUrl('/admin');
    } else if (user.role === Role.SCHOOL) {
      this.router.navigateByUrl('/client');
    }
    console.log('Roles are: ' + user.role);
    return true;
  }

}
