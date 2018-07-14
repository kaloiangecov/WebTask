import { Component } from '@angular/core';
import { TokenStorage } from './shared/authentication/token.storage';
import { AuthService } from './shared/authentication/auth.service';
import { Router } from '@angular/router';
import { UserService } from './user/user.service';
import { User } from './user/user';
import { ResizeEvent } from 'angular-resizable-element';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  private activeUser: User;
  private style: object = {};
  private containerStyle: object = {};
  private resizableWidth = 25;

  constructor(private token: TokenStorage,
              private authService: AuthService,
              private router: Router,
              private userService: UserService) {

    router.events.subscribe((val) => {
      this.activeUser = authService.getLoggedUser();
    });
  }

  validate(event: ResizeEvent): boolean {
    const MIN_DIMENSIONS_PX = 25;
    const MAX_DIMENSIONS_PX = 250;
    if (event.rectangle.width &&
        (event.rectangle.width < MIN_DIMENSIONS_PX ||
         event.rectangle.width > MAX_DIMENSIONS_PX)
      ) {
      return false;
    }
    return true;
  }

  onResizeEnd(event: ResizeEvent): void {
    this.resizableWidth = event.rectangle.width;
    this.style = {
      position: 'absolute',
      left: `${event.rectangle.left}px`,
      width: `${event.rectangle.width}px`,
    };

    this.containerStyle = {
      position: 'absolute',
      left: `${event.rectangle.width}px`,
    };
  }

  logout() {
    this.authService.logout();
  }

  editProfile() {
    this.router.navigate(['/users/edit/' + this.activeUser.id]);
  }

  login() {
    this.router.navigate(['/login']);
  }
}
