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
    return this.http.get(this.apiUrl).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  findById(id: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + id).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  saveUser(user: User): Observable<any> {
    return this.http.post(this.apiUrl, user).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  signup(user: User): Observable<any> {
    return this.http.post('http://localhost:8080/WebTask/signup', user).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  deleteUserById(id: number): Observable<any> {
    return this.http.delete(this.apiUrl + '/' + id).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  updateUser(user: User): Observable<any> {
    return this.http.put(this.apiUrl + '/' + user.id, user).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  countUsers(): Observable<any> {
    return this.http.get(this.apiUrl + '/count').pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  findAllUsersPageable(page: number, elementsPerPage: number, searchTerm?: string): Observable<any> {
    let searchParam = '';
    if (searchTerm != null) {
      searchParam = '&searchTerm=' + searchTerm;
    }
    return this.http.get(this.apiUrl + '/page?page=' + page +
                        '&elements_per_page=' + elementsPerPage + searchParam).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  usernameExists(username: string, id): Observable<any> {
    return this.http.get(this.apiUrl + '/exists/username?username=' + username + '&id=' + id).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  emailExists(email: string, id): Observable<any> {
    return this.http.get(this.apiUrl + '/exists/email?email=' + email + '&id=' + id).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  findByUsername(username: string): Observable<any> {
    return this.http.get(this.apiUrl + '/username/' + username).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }
}
