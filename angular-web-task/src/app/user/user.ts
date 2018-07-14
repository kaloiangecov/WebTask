import { Role } from './role';

export class User {
  id: number;
  username: string;
  email: string;
  password: string;
  role: Role;

  constructor(id?: number, username?: string, email?: string, password?: string, role?: Role) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
  }

}
