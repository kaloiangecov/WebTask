import { throwError as observableThrowError,  Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { Discipline } from './discipline';
import { DisciplineModule } from './discipline.module';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class DisciplineService {

  private apiUrl = 'http://localhost:8080/WebTask/disciplines';

  constructor(private http: HttpClient) { }

  findAll(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  findById(id: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + id);
  }

  save(discipline: Discipline): Observable<any> {
    return this.http.post(this.apiUrl, discipline);
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(this.apiUrl + '/' + id);
  }

  update(discipline: Discipline): Observable<any> {
    return this.http.put(this.apiUrl + '/' + discipline.id, discipline);
  }

  findAllDisciplinesPageable(page: number, elementsPerPage: number, searchTerm?: string): Observable<any> {
    let searchParam = '';
    if (searchTerm != null) {
      searchParam = '&searchTerm=' + searchTerm;
    }
    return this.http.get(this.apiUrl + '/page?page=' + page +
                        '&elements_per_page=' + elementsPerPage + searchParam);
  }
}
