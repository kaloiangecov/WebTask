import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenStorage } from './token.storage';
import { Router } from '@angular/router';
import { User } from '../../user/user';


@Injectable()
export class AuthService {

  private tokenUrl = 'http://localhost:8080/WebTask/token/generate-token';
  private USER_KEY = 'active_user';
  private activeUser: User;

  constructor(private token: TokenStorage,
              private router: Router,
              private http: HttpClient) {
  }

  attemptAuth(ussername: string, password: string): Observable<any> {
    const credentials = {username: ussername, password: password};
    return this.http.post(this.tokenUrl, credentials);
  }

  checkIfLogged(): boolean {
    const token = this.token.getToken();
    if (token != null) {
      return true;
    }
    return false;
  }

  isAdmin(): boolean {
    if  (this.activeUser == null) {
      this.activeUser = JSON.parse(window.sessionStorage.getItem(this.USER_KEY));
    }
    if (this.activeUser.role.name === 'ADMIN') {
      return true;
    }
    return false;
  }

  getLoggedUser(): User {
    return JSON.parse(window.sessionStorage.getItem(this.USER_KEY));
  }

  logout() {
    this.activeUser = null;
    this.token.signOut();
    window.sessionStorage.removeItem(this.USER_KEY);
    this.router.navigate(['/login']);
  }

}
