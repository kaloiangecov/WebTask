import { Component, OnInit } from '@angular/core';
import { Department } from '../department';
import { DepartmentService } from '../department.service';
import { Router } from '@angular/router';
import { Subject as RxSubject, Observable } from 'rxjs';
import { debounceTime } from 'rxjs/operators';


@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.css']
})
export class DepartmentListComponent implements OnInit {

  private departments: Department[];
  private page: number;
  private maxPageSize: number;
  private elementsPerPage: number;
  private searchUpdated: RxSubject<string> = new RxSubject<string>();

  constructor(private router: Router,
              private departmentService: DepartmentService) {

    this.searchUpdated.pipe(debounceTime(500)).subscribe(searchTerm => {
      this.departmentService.findAllDepartmentsPageable(this.page - 1, this.elementsPerPage, searchTerm).subscribe(
        result => {
          this.maxPageSize = +(result.totalPages + '0');
          this.departments = result.content;
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
    this.getDepartmentsPage();
  }

  getDepartmentsPage() {
    this.departmentService.findAllDepartmentsPageable(this.page - 1, this.elementsPerPage).subscribe(
      result => {
        this.maxPageSize = +(result.totalPages + '0');
        this.departments = result.content;
      },
      err => {
        console.log(err);
      }
    );
  }

  edit(department: Department) {
    if (department) {
      this.router.navigate(['/departments/edit', department.id]);
    }
  }

  delete(department: Department) {
    this.departmentService.deleteById(department.id).subscribe(
      result => {
        this.departments.splice(this.departments.indexOf(department), 1);
      },
      err => {
        console.log(err);
      }
    );
  }

  viewDetails(department: Department) {
    this.router.navigate(['/departments/details', department.id]);
  }

  create() {
    this.router.navigate(['/departments/create']);
  }

  onSearchType(value: string) {
    this.searchUpdated.next(value);
  }
}
