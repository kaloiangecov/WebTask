import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Department } from './department';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class DepartmentService {

  private apiUrl = 'http://localhost:8080/WebTask/departments';

  constructor(private http: HttpClient) { }

  findAll(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  findById(id: number): Observable<any> {
    return this.http.get(this.apiUrl + '/' + id);
  }

  save(department: Department): Observable<any> {
    return this.http.post(this.apiUrl, department);
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(this.apiUrl + '/' + id);
  }

  update(department: Department): Observable<any> {
    return this.http.put(this.apiUrl + '/' + department.id, department);
  }

  findAllDepartmentsPageable(page: number, elementsPerPage: number, searchTerm?: string): Observable<any> {
    let searchParam = '';
    if (searchTerm != null) {
      searchParam = '&searchTerm=' + searchTerm;
    }
    return this.http.get(this.apiUrl + '/page?page=' + page +
                        '&elements_per_page=' + elementsPerPage + searchParam);
  }
}
