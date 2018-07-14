import { Component, OnInit } from '@angular/core';
import { Faculty } from '../faculty';
import { FacultyService } from '../faculty.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../../../shared/authentication/auth.service';


@Component({
  selector: 'app-faculty-create',
  templateUrl: './faculty-create.component.html',
  styleUrls: ['./faculty-create.component.css']
})
export class FacultyCreateComponent implements OnInit {

  private faculty: Faculty;
  private updateScenario: boolean;

  constructor(private facultyService: FacultyService,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService) { }

  ngOnInit() {
    this.initFaculty();
  }

  initFaculty() {
    this.route.paramMap.subscribe(
      params => {
        const id = +params.get('id');
        if (id) {
          this.facultyService.findById(id).subscribe(
            (faculty: Faculty) => {
              if (faculty) {
                this.updateScenario = true;
                this.faculty = faculty;
              } else {
                console.log(`Faculty with id '${id}' not found, returning to list`);
                this.navigateToList();
              }
            });
        } else {
          this.updateScenario = false;
          this.faculty = new Faculty();
        }
      });
  }

  processForm() {
    if (this.updateScenario === false) {
      this.facultyService.save(this.faculty).subscribe(
        result => {
          this.navigateToList();
        },
        error => console.error(error));
    } else {
      this.facultyService.update(this.faculty).subscribe(
        result => {
          this.navigateToList();
        },
        error => console.error(error));
    }

  }

  navigateToList() {
      this.router.navigate(['/faculties']);
  }
}
