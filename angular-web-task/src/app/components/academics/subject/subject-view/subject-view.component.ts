import { Component, OnInit } from '@angular/core';
import { Subject } from '../subject';
import { SubjectService } from '../subject.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../../../shared/authentication/auth.service';


@Component({
  selector: 'app-subject-view',
  templateUrl: './subject-view.component.html',
  styleUrls: ['./subject-view.component.css']
})
export class SubjectViewComponent implements OnInit {

  private subject: Subject;

  constructor(private subjectService: SubjectService,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService) { }

  ngOnInit() {
    this.initSubject();
  }

  initSubject() {
    this.route.paramMap.subscribe(
      params => {
        const id = +params.get('id');
        this.subjectService.findById(id).subscribe(
          (subject: Subject) => {
            this.subject = subject;
          },
          error => {
            console.log(`Subject with id '${id}' not found, returning to list`);
            this.router.navigate(['/subjects']);
          });
      });
  }

}
