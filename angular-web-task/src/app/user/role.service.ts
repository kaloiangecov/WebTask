import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Role } from './role';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class RoleService {

  private apiUrl = 'http://localhost:8080/WebTask/roles';

  constructor(private http: HttpClient) {
  }

  findAll(): Observable<any>  {
    return this.http.get(this.apiUrl);
  }
}
