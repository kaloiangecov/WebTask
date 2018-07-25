import { Component, OnInit } from '@angular/core';
import { Discipline } from '../discipline';
import { DisciplineService } from '../discipline.service';
import { SubjectService } from '../../subject/subject.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../../../shared/authentication/auth.service';
import { Subject } from '../../subject/subject';
import { CompleterService, CompleterData, CompleterItem } from 'ng2-completer';

@Component({
  selector: 'app-discipline-view',
  templateUrl: './discipline-view.component.html',
  styleUrls: ['./discipline-view.component.css']
})
export class DisciplineViewComponent implements OnInit {

  private discipline: Discipline;
  private subjects: Subject[];
  private allSubjects: Subject[];
  private arrayChanged: boolean;
  private searchStr: string;
  private dataService: CompleterData;
  private selectedItem: string;
  private semesters: number[];
  private selectedSemester: number;

  constructor(private disciplineService: DisciplineService,
              private router: Router,
              private route: ActivatedRoute,
              private subjectService: SubjectService,
              private authService: AuthService,
              private completerService: CompleterService) { }

  ngOnInit() {
    this.selectedSemester = -1;
    this.arrayChanged = false;
    this.initDiscipline();
  }

  initSubjects() {
    this.subjectService.findAll().subscribe(
      (subjects: Subject[]) => {
        this.allSubjects = subjects;
        this.subjects = this.filterArray(this.discipline.subjects, subjects);
        this.dataService = this.completerService.local(this.subjects, 'name', 'name');
      }
    );
  }

  initDiscipline() {
    this.route.paramMap.subscribe(
      params => {
        const id = +params.get('id');
        this.disciplineService.findById(id).subscribe(
          (discipline: Discipline) => {
            this.discipline = discipline;
            this.semesters = Array.apply(null, {length: discipline.numberOfSemesters}).map(Number.call, Number);
            this.initSubjects();
          },
          error => {
            console.log(`Department with id '${id}' not found, returning to list`);
            this.router.navigate(['/disciplines']);
          });
      });
  }

  onSelected(item: CompleterItem) {
    this.selectedItem = item ? item.title : ' ';
  }

  addSelectedItem() {
    this.arrayChanged = true;
    const selectedSubject: Subject = this.subjects.find(subject => subject.name === this.selectedItem);
    selectedSubject.semester = this.selectedSemester;
    this.discipline.subjects.push(selectedSubject);
    this.selectedSemester = -1;
    // this.subjects = this.filterArray(this.discipline.subjects, this.allSubjects);
  }

  removeSelectedItem(subject: Subject) {
    this.arrayChanged = true;
    this.discipline.subjects.splice(this.discipline.subjects.indexOf(subject), 1);
    // this.subjects = this.filterArray(this.discipline.subjects, this.allSubjects);
  }

  saveChanges() {
    this.disciplineService.update(this.discipline).subscribe(
      (discipline: Discipline) => {
        this.discipline = discipline;
        this.selectedSemester = -1;
        alert('changes made');
      });
  }

  filterArray(disciplineSubjects: Subject[], subjects: Subject[]): Subject[] {
    const result: Subject[] = [];
    for (let i = 0; i < subjects.length; i++) {
      if (!this.contains(subjects[i], disciplineSubjects)) {
        result.push(subjects[i]);
      }
    }
    return result;
  }

  contains(subject: Subject, subjects: Subject[]): boolean {
    for (let i = 0; i < subjects.length; i++) {
      if (subjects[i].code === subject.code) {
        return true;
      }
    }
    return false;
  }
}
