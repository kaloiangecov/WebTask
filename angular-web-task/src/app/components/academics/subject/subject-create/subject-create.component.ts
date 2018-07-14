import { Component, OnInit } from '@angular/core';
import { Subject } from '../subject';
import { SubjectService } from '../subject.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { AuthService } from '../../../../shared/authentication/auth.service';


@Component({
  selector: 'app-subject-create',
  templateUrl: './subject-create.component.html',
  styleUrls: ['./subject-create.component.css']
})
export class SubjectCreateComponent implements OnInit {

  private subject: Subject;
  private updateScenario: boolean;

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
        if (id) {
          this.subjectService.findById(id).subscribe(
            (subject: Subject) => {
              if (subject) {
                this.updateScenario = true;
                this.subject = subject;
              } else {
                console.log(`Subject with id '${id}' not found, returning to list`);
                this.navigateToList();
              }
            });
        } else {
          this.updateScenario = false;
          this.subject = new Subject();
        }
      });
  }

  processForm() {
    if (this.updateScenario === false) {
      this.subjectService.save(this.subject).subscribe(
        result => {
          this.navigateToList();
        },
        error => console.error(error));
    } else {
      this.subjectService.update(this.subject).subscribe(
        result => {
          this.navigateToList();
        },
        error => console.error(error));
    }

  }

  navigateToList() {
      this.router.navigate(['/subjects']);
  }
}
