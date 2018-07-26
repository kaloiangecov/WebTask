import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AcademicsModule } from './academics.module';

@Injectable()
export class AcademicsService {

  private apiUrl = 'http://localhost:8080/WebTask/academics';

  constructor(private http: HttpClient) { }

  codeExists(code: string, id): Observable<any> {
    return this.http.get(this.apiUrl + '/exists/code?code=' + code + '&id=' + id);
  }

}
