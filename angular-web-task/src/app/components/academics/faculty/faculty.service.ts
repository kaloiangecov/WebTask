import { throwError as observableThrowError,  Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { Faculty } from './faculty';
import { FacultyModule } from './faculty.module';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class FacultyService {

  private apiUrl = 'http://localhost:8080/WebTask/faculties';

  constructor(private http: HttpClient) { }

  findAll(): Observable<any> {
    return this.http.get(this.apiUrl).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  findById(id: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + id).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  save(faculty: Faculty): Observable<any> {
    return this.http.post(this.apiUrl, faculty).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(this.apiUrl + '/' + id).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  update(faculty: Faculty): Observable<any> {
    return this.http.put(this.apiUrl + '/' + faculty.id, faculty).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }

  findAllFacultiesPageable(page: number, elementsPerPage: number, searchTerm?: string): Observable<any> {
    let searchParam = '';
    if (searchTerm != null) {
      searchParam = '&searchTerm=' + searchTerm;
    }
    return this.http.get(this.apiUrl + '/page?page=' + page +
                        '&elements_per_page=' + elementsPerPage + searchParam).pipe(
      catchError((error: any) => observableThrowError(error.json().error || 'Server error')));
  }
}
