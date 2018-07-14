import { Component, OnInit } from '@angular/core';
import { Faculty } from '../faculty';
import { FacultyService } from '../faculty.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../../../shared/authentication/auth.service';

@Component({
  selector: 'app-faculty-view',
  templateUrl: './faculty-view.component.html',
  styleUrls: ['./faculty-view.component.css']
})
export class FacultyViewComponent implements OnInit {

  private faculty: Faculty;

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
        this.facultyService.findById(id).subscribe(
          (faculty: Faculty) => {
            this.faculty = faculty;
          },
          error => {
            console.log(`Faculty with id '${id}' not found, returning to list`);
            this.router.navigate(['/faculties']);
          });
      });
  }

}
