import { Component, OnInit } from '@angular/core';
import { Department } from '../department';
import { DepartmentService } from '../department.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../../../shared/authentication/auth.service';

@Component({
  selector: 'app-department-view',
  templateUrl: './department-view.component.html',
  styleUrls: ['./department-view.component.css']
})
export class DepartmentViewComponent implements OnInit {

  private department: Department;

  constructor(private departmentService: DepartmentService,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService) { }

  ngOnInit() {
    this.initDepartment();
  }

  initDepartment() {
    this.route.paramMap.subscribe(
      params => {
        const id = +params.get('id');
        this.departmentService.findById(id).subscribe(
          (department: Department) => {
            this.department = department;
          },
          error => {
            console.log(`Department with id '${id}' not found, returning to list`);
            this.router.navigate(['/departments']);
          });
      });
  }

}
