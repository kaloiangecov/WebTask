import { Component, OnInit } from '@angular/core';
import { Faculty } from '../../faculty/faculty';
import { Department } from '../department';
import { FacultyService } from '../../faculty/faculty.service';
import { DepartmentService } from '../department.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../../../shared/authentication/auth.service';


@Component({
  selector: 'app-department-create',
  templateUrl: './department-create.component.html',
  styleUrls: ['./department-create.component.css']
})
export class DepartmentCreateComponent implements OnInit {

  private faculties: Faculty[];
  private selectedFaculty: number;
  private department: Department;
  private updateScenario: boolean;

  constructor(private facultyService: FacultyService,
              private departmentService: DepartmentService,
              private authService: AuthService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.initDepartment();
    this.fetchFaculties();
  }

  fetchFaculties() {
    this.facultyService.findAll().subscribe(
      (faculties: Faculty[]) => {
        this.faculties = faculties;
      }
    );
  }

  initDepartment() {
    this.route.paramMap.subscribe(
      params => {
        const id = +params.get('id');
        if (id) {
          this.departmentService.findById(id).subscribe(
            (department: Department) => {
              this.updateScenario = true;
              this.selectedFaculty = department.faculty.id;
              this.department = department;
            },
            error => {
              console.log(`Department with id '${id}' not found, returning to list`);
              this.navigateToList();
            });
        } else {
          this.selectedFaculty = -1;
          this.updateScenario = false;
          this.department = new Department();
        }
      });
  }

  processForm() {
    this.department.faculty = this.faculties.find(faculty => faculty.id === +this.selectedFaculty);
    if (this.updateScenario === false) {
      this.departmentService.save(this.department).subscribe(
        result => {
          this.navigateToList();
        });
    } else {
      this.departmentService.update(this.department).subscribe(
        result => {
          this.navigateToList();
        });
    }

  }

  navigateToList() {
      this.router.navigate(['/departments']);
  }

}
