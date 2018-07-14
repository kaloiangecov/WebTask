import { Component, OnInit } from '@angular/core';
import { Subject } from '../subject';
import { SubjectService } from '../subject.service';
import { Router } from '@angular/router';
import { Subject as RxSubject, Observable } from 'rxjs';
import { debounceTime } from 'rxjs/operators';


@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css']
})
export class SubjectListComponent implements OnInit {

  private subjects: Subject[];
  private page: number;
  private maxPageSize: number;
  private elementsPerPage: number;
  private searchUpdated: RxSubject<string> = new RxSubject<string>();

  constructor(private router: Router,
              private subjectService: SubjectService) {

    this.searchUpdated.pipe(debounceTime(500)).subscribe(searchTerm => {
      this.subjectService.findAllSubjectsPageable(this.page - 1, this.elementsPerPage, searchTerm).subscribe(
        result => {
          this.maxPageSize = +(result.totalPages + '0');
          this.subjects = result.content;
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
    this.getSubjectsPage();
  }

  getSubjectsPage() {
    this.subjectService.findAllSubjectsPageable(this.page - 1, this.elementsPerPage).subscribe(
      result => {
        this.maxPageSize = +(result.totalPages + '0');
        this.subjects = result.content;
      },
      err => {
        console.log(err);
      }
    );
  }

  edit(subject: Subject) {
    if (subject) {
      this.router.navigate(['/subjects/edit', subject.id]);
    }
  }

  delete(subject: Subject) {
    this.subjectService.deleteById(subject.id).subscribe(
      result => {
        this.subjects.splice(this.subjects.indexOf(subject), 1);
      },
      err => {
        console.log(err);
      }
    );
  }

  viewDetails(subject: Subject) {
    this.router.navigate(['/subjects/details', subject.id]);
  }

  create() {
    this.router.navigate(['/subjects/create']);
  }

  onSearchType(value: string) {
    this.searchUpdated.next(value);
  }
}
