import { throwError as observableThrowError,  Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { User } from './user';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class UserService {

  private apiUrl = 'http://localhost:8080/WebTask/users';

  constructor(private http: HttpClient) { }

  findAll(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  findById(id: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + id);
  }

  saveUser(user: User): Observable<any> {
    return this.http.post(this.apiUrl, user);
  }

  signup(user: User): Observable<any> {
    return this.http.post('http://localhost:8080/WebTask/signup', user);
  }

  deleteUserById(id: number): Observable<any> {
    return this.http.delete(this.apiUrl + '/' + id);
  }

  updateUser(user: User): Observable<any> {
    return this.http.put(this.apiUrl + '/' + user.id, user);
  }

  countUsers(): Observable<any> {
    return this.http.get(this.apiUrl + '/count');
  }

  findAllUsersPageable(page: number, elementsPerPage: number, searchTerm?: string): Observable<any> {
    let searchParam = '';
    if (searchTerm != null) {
      searchParam = '&searchTerm=' + searchTerm;
    }
    return this.http.get(this.apiUrl + '/page?page=' + page +
                        '&elements_per_page=' + elementsPerPage + searchParam);
  }

  usernameExists(username: string, id): Observable<any> {
    return this.http.get(this.apiUrl + '/exists/username?username=' + username + '&id=' + id);
  }

  emailExists(email: string, id): Observable<any> {
    return this.http.get(this.apiUrl + '/exists/email?email=' + email + '&id=' + id);
  }

  findByUsername(username: string): Observable<any> {
    return this.http.get(this.apiUrl + '/username/' + username);
  }
}
