import { NgModule, ModuleWithProviders } from '@angular/core';
import { FacultyService } from './faculty/faculty.service';
import { FacultyModule } from './faculty/faculty.module';
import { DepartmentService } from './department/department.service';
import { DepartmentModule } from './department/department.module';
import { DisciplineService } from './discipline/discipline.service';
import { DisciplineModule } from './discipline/discipline.module';
import { SubjectService } from './subject/subject.service';
import { SubjectModule } from './subject/subject.module';

@NgModule({
  imports: [
    FacultyModule,
    DepartmentModule,
    DisciplineModule,
    SubjectModule
  ],
  declarations: [

  ],
  exports: [
    FacultyModule,
    DepartmentModule,
    DisciplineModule,
    SubjectModule
  ]
})
export class AcademicsModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AcademicsModule,
      providers: [ FacultyService, DepartmentService, DisciplineService, SubjectService]
    };
  }
}
