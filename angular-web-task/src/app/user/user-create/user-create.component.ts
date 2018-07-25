import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { RoleService } from '../role.service';
import { AuthService } from '../../shared/authentication/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Role } from '../role';


@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {

  private user: User;
  private roles: Role[];
  private selectedRole: number;
  private confirmPassword: string;
  private updateUser: boolean;

  constructor(private userService: UserService,
              private roleService: RoleService,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService) { }

  ngOnInit() {
    this.fetchRoles();
    this.initUser();
  }

  fetchRoles() {
    this.roleService.findAll().subscribe(
      (roles: Role[]) => {
        this.roles = roles;
      }
    );
  }

  initUser() {
    this.route.paramMap.subscribe(
      params => {
        const id = +params.get('id');
        if (id) {
          this.userService.findById(id).subscribe(
            (user: User) => {
              this.updateUser = true;
              this.selectedRole = user.role.id;
              this.user = user;
              this.user.password = null;
            },
          error => {
            console.log(`User with id '${id}' not found, returning to list`);
            this.gotoUserList();
          });
        } else {
          this.updateUser = false;
          this.user = new User();
          this.selectedRole = -1;
        }
      });
  }

  processForm() {
    this.user.role = this.roles.find(role => role.id === +this.selectedRole);
    if (this.updateUser === false) {
      this.userService.saveUser(this.user).subscribe(
        result => {
          this.gotoUserList();
        });
    } else {
      this.userService.updateUser(this.user).subscribe(
        result => {
          this.gotoUserList();
        });
    }

  }

  gotoUserList() {
      this.router.navigate(['/users']);
  }

}
