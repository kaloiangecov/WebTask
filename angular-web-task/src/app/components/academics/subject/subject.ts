
export class Subject {

  id: number;
  name: string;
  code: string;
  description: string;
  credits: string;
  semester = 0;

  constructor(id?: number, name?: string, code?: string,
              description?: string, credits?: string, semester?: number) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.description = description;
    this.credits = credits;
    this.semester = semester;
  }
}
