import { throwError as observableThrowError,  Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { Department } from './department';
import { DepartmentModule } from './department.module';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class DepartmentService {

  private apiUrl = 'http://localhost:8080/WebTask/departments';

  constructor(private http: HttpClient) { }

  findAll(): Observable<any> {
    return this.http.get(this.apiUrl).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  findById(id: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + id).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  save(department: Department): Observable<any> {
    return this.http.post(this.apiUrl, department).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(this.apiUrl + '/' + id).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  update(department: Department): Observable<any> {
    return this.http.put(this.apiUrl + '/' + department.id, department).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  findAllDepartmentsPageable(page: number, elementsPerPage: number, searchTerm?: string): Observable<any> {
    let searchParam = '';
    if (searchTerm != null) {
      searchParam = '&searchTerm=' + searchTerm;
    }
    return this.http.get(this.apiUrl + '/page?page=' + page +
                        '&elements_per_page=' + elementsPerPage + searchParam).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }
}
