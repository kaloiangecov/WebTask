import { Injectable } from '@angular/core';
import { Router, CanActivate,
         ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../auth.service';
import { User } from '../../../user/user';

@Injectable()
export class AdminGuardService implements CanActivate {

  private user: User;

  constructor(private authService: AuthService,
              private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    this.user = this.authService.getLoggedUser();

    if (this.user.role.name !== 'ADMIN') {
      // TODO: make some error page for such case
      this.router.navigate(['home']);
      return false;
    }

    return true;
  }

}
