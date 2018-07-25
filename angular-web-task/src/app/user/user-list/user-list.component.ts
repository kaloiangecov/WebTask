import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { Subject, Observable } from 'rxjs';
import { debounceTime } from 'rxjs/operators';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  private users: User[];
  private page: number;
  private maxPageSize: number;
  private elementsPerPage: number;
  private searchUpdated: Subject<string> = new Subject<string>();

  constructor(private router: Router,
              private userService: UserService) {

    this.searchUpdated.pipe(debounceTime(500)).subscribe(searchTerm => {
      this.userService.findAllUsersPageable(this.page - 1, this.elementsPerPage, searchTerm).subscribe(
        result => {
          this.maxPageSize = +(result.totalPages + '0');
          this.users = result.content;
        }
      );
    });
  }

  ngOnInit() {
    this.elementsPerPage = 5;
    this.page = 1;
    this.getUsersPage();
  }

  getUsersPage() {
    this.userService.findAllUsersPageable(this.page - 1, this.elementsPerPage).subscribe(
      result => {
        this.maxPageSize = +(result.totalPages + '0');
        this.users = result.content;
      }
    );
  }

  editUserPage(user: User) {
    if (user) {
      this.router.navigate(['/users/edit', user.id]);
    }
  }

  deleteUser(user: User) {
    this.userService.deleteUserById(user.id).subscribe(
      result => {
        this.users.splice(this.users.indexOf(user), 1);
      }
    );
  }

  redirectNewUserPage() {
    this.router.navigate(['/users/create']);
  }

  onSearchType(value: string) {
    this.searchUpdated.next(value);
  }

}
