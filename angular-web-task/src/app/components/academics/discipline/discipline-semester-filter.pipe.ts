import { Pipe, PipeTransform } from '@angular/core';
import { Subject } from '../subject/subject';

@Pipe({
  name: 'filterBySemester',
  pure: true
})
export class DisciplineSemesterFilterPipe implements PipeTransform {
  transform(subjects: Subject[], semester: number): Subject[] {
    if (!subjects || !semester) {
      return subjects;
    }

    return subjects.filter(subject => +subject.semester === +semester);
  }
}
