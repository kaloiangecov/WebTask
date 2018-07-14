import { Component, OnInit } from '@angular/core';
import { Faculty } from '../faculty';
import { FacultyService } from '../faculty.service';
import { Router } from '@angular/router';
import { Subject as RxSubject, Observable } from 'rxjs';
import { debounceTime } from 'rxjs/operators';


@Component({
  selector: 'app-faculty-list',
  templateUrl: './faculty-list.component.html',
  styleUrls: ['./faculty-list.component.css']
})
export class FacultyListComponent implements OnInit {

  private faculties: Faculty[];
  private page: number;
  private maxPageSize: number;
  private elementsPerPage: number;
  private searchUpdated: RxSubject<string> = new RxSubject<string>();

  constructor(private router: Router,
              private facultyService: FacultyService) {

    this.searchUpdated.pipe(debounceTime(500)).subscribe(searchTerm => {
      this.facultyService.findAllFacultiesPageable(this.page - 1, this.elementsPerPage, searchTerm).subscribe(
        result => {
          this.maxPageSize = +(result.totalPages + '0');
          this.faculties = result.content;
        },
        err => {
          console.log(err);
        }
      );
    });
  }

  ngOnInit() {
    this.elementsPerPage = 5;
    this.page = 1;
    this.getFacultiesPage();
  }

  getFacultiesPage() {
    this.facultyService.findAllFacultiesPageable(this.page - 1, this.elementsPerPage).subscribe(
      result => {
        this.maxPageSize = +(result.totalPages + '0');
        this.faculties = result.content;
      },
      err => {
        console.log(err);
      }
    );
  }

  edit(faculty: Faculty) {
    if (faculty) {
      this.router.navigate(['/faculties/edit', faculty.id]);
    }
  }

  delete(faculty: Faculty) {
    this.facultyService.deleteById(faculty.id).subscribe(
      result => {
        this.faculties.splice(this.faculties.indexOf(faculty), 1);
      },
      err => {
        console.log(err);
      }
    );
  }

  viewDetails(faculty: Faculty) {
    this.router.navigate(['/faculties/details', faculty.id]);
  }

  create() {
    this.router.navigate(['/faculties/create']);
  }

  onSearchType(value: string) {
    this.searchUpdated.next(value);
  }
}
