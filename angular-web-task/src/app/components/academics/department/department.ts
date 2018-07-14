import { Faculty } from '../faculty/faculty';

export class Department {

  id: number;
  name: string;
  code: string;
  description: string;
  faculty: Faculty;

  constructor(id?: number, name?: string, code?: string, description?: string, faculty?: Faculty) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.description = description;
    this.faculty = faculty;
  }
}
