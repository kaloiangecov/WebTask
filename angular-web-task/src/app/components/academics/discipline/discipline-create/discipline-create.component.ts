import { Component, OnInit } from '@angular/core';
import { Department } from '../../department/department';
import { Discipline } from '../discipline';
import { DepartmentService } from '../../department/department.service';
import { DisciplineService } from '../discipline.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../../../shared/authentication/auth.service';


@Component({
  selector: 'app-discipline-create',
  templateUrl: './discipline-create.component.html',
  styleUrls: ['./discipline-create.component.css']
})
export class DisciplineCreateComponent implements OnInit {

  private departments: Department[];
  private selectedDepartment: number;
  private selectedEducationType: any;
  private selectedEducationDegree: any;
  private selectedNumberOfSemesters: number;
  private semesters: number[];
  private discipline: Discipline;
  private updateScenario: boolean;

  constructor(private disciplineService: DisciplineService,
              private departmentService: DepartmentService,
              private authService: AuthService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.semesters = Array.apply(null, {length: 10}).map(Number.call, Number);
    this.initDiscipline();
    this.initDepartments();
  }

  initDepartments() {
    this.departmentService.findAll().subscribe(
      (departments: Department[]) => {
        this.departments = departments;
      }
    );
  }

  initDiscipline() {
    this.route.paramMap.subscribe(
      params => {
        const id = +params.get('id');
        if (id) {
          this.disciplineService.findById(id).subscribe(
            (discipline: Discipline) => {
              this.updateScenario = true;
              this.selectedDepartment = discipline.department.id;
              this.selectedEducationType = discipline.educationType;
              this.selectedEducationDegree = discipline.educationDegree;
              this.selectedNumberOfSemesters = discipline.numberOfSemesters;
              this.discipline = discipline;
            },
            error => {
              console.log(`Discipline with id '${id}' not found, returning to list`);
              this.navigateToList();
            });
        } else {
          this.selectedDepartment = -1;
          this.selectedEducationType = -1;
          this.selectedEducationDegree = -1;
          this.selectedNumberOfSemesters = -1;
          this.updateScenario = false;
          this.discipline = new Discipline();
        }
      });
  }

  processForm() {
    this.discipline.department = this.departments.find(department => department.id === +this.selectedDepartment);
    this.discipline.numberOfSemesters = this.selectedNumberOfSemesters;
    this.discipline.educationType = this.selectedEducationType;
    this.discipline.educationDegree = this.selectedEducationDegree;

    if (this.updateScenario === false) {
      this.disciplineService.save(this.discipline).subscribe(
        result => {
          this.navigateToList();
        });
    } else {
      this.disciplineService.update(this.discipline).subscribe(
        result => {
          this.navigateToList();
        });
    }

  }

  navigateToList() {
      this.router.navigate(['/disciplines']);
  }

}
