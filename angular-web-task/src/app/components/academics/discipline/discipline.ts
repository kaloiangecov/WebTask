import { Department } from '../department/department';
import { Subject } from '../subject/subject';
export class Discipline {

  id: number;
  name: string;
  code: string;
  description: string;
  department: Department;
  educationType: string;
  educationDegree: string;
  numberOfSemesters: number;
  subjects: Subject[];

  constructor(id?: number, name?: string, code?: string, description?: string,
              department?: Department, educationType?: string, educationDegree?: string,
              numberOfSemesters?: number, subjects?: Subject[]) {

    this.id = id;
    this.name = name;
    this.code = code;
    this.description = description;
    this.department = department;
    this.educationType = educationType;
    this.educationDegree = educationDegree;
    this.numberOfSemesters = numberOfSemesters;
  }
}
