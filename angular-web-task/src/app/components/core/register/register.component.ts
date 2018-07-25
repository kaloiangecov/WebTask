import { Component, OnInit } from '@angular/core';
import { User } from '../../../user/user';
import { UserService } from '../../../user/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  private user: User;
  private confirmPassword: string;

  constructor(private userService: UserService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.user = new User();
  }

  processForm() {
    this.userService.signup(this.user).subscribe(
      result => {
        this.router.navigate(['login']);
      });
  }

}
