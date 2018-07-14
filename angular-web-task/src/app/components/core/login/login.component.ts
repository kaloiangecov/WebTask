import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../../user/user.service';
import { AuthService } from '../../../shared/authentication/auth.service';
import { TokenStorage } from '../../../shared/authentication/token.storage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  private USER_KEY = 'active_user';
  private invalidCredentials: boolean;

  constructor(private router: Router,
              private authService: AuthService,
              private token: TokenStorage,
              private userService: UserService) { }

  ngOnInit() {
    this.resetCredentialValidity();
  }

  resetCredentialValidity() {
    this.invalidCredentials = false;
  }

  logout() {
    this.authService.logout();
  }

  login(): void {
    this.authService.attemptAuth(this.username, this.password).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.setActiveUser();
        setTimeout(() => {
          this.router.navigate(['/home']);
        }, 250);
      },
      error => {
        this.invalidCredentials = true;
      }
    );
  }

  setActiveUser() {
    this.userService.findByUsername(this.username).subscribe(
      data => {
        window.sessionStorage.setItem(this.USER_KEY, JSON.stringify(data));
      }
    );

  }
}
