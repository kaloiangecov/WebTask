import { Component, OnInit } from '@angular/core';
import { Discipline } from '../discipline';
import { DisciplineService } from '../discipline.service';
import { Router } from '@angular/router';
import { Subject as RxSubject, Observable } from 'rxjs';
import { debounceTime } from 'rxjs/operators';


@Component({
  selector: 'app-discipline-list',
  templateUrl: './discipline-list.component.html',
  styleUrls: ['./discipline-list.component.css']
})
export class DisciplineListComponent implements OnInit {

  private disciplines: Discipline[];
  private page: number;
  private maxPageSize: number;
  private elementsPerPage: number;
  private searchUpdated: RxSubject<string> = new RxSubject<string>();


  constructor(private router: Router,
              private disciplineService: DisciplineService) {

    this.searchUpdated.pipe(debounceTime(500)).subscribe(searchTerm => {
      this.disciplineService.findAllDisciplinesPageable(this.page - 1, this.elementsPerPage, searchTerm).subscribe(
        result => {
          this.maxPageSize = +(result.totalPages + '0');
          this.disciplines = result.content;
        });
    });
  }

  ngOnInit() {
    this.elementsPerPage = 5;
    this.page = 1;
    this.getDisciplinesPage();
  }

  getDisciplinesPage() {
    this.disciplineService.findAllDisciplinesPageable(this.page - 1, this.elementsPerPage).subscribe(
      result => {
        this.maxPageSize = +(result.totalPages + '0');
        this.disciplines = result.content;
      });
  }

  edit(discipline: Discipline) {
    if (discipline) {
      this.router.navigate(['/disciplines/edit', discipline.id]);
    }
  }

  delete(discipline: Discipline) {
    this.disciplineService.deleteById(discipline.id).subscribe(
      result => {
        this.disciplines.splice(this.disciplines.indexOf(discipline), 1);
      });
  }

  viewDetails(discipline: Discipline) {
    this.router.navigate(['/disciplines/details', discipline.id]);
  }

  create() {
    this.router.navigate(['/disciplines/create']);
  }

  onSearchType(value: string) {
    this.searchUpdated.next(value);
  }
}
