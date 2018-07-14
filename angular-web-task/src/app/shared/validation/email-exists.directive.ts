import { timer as observableTimer,  Subscription ,  Observable } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { Directive, Input, OnInit } from '@angular/core';
import { AsyncValidator, NG_ASYNC_VALIDATORS, AbstractControl, ValidationErrors, AsyncValidatorFn } from '@angular/forms';
import { UserService } from '../../user/user.service';
import { ActivatedRoute, Router } from '@angular/router';


export function existingEmailValidator(userService: UserService, id: number): AsyncValidatorFn {
  return (control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> => {
    const debounceTime = 500;
    return observableTimer(debounceTime).pipe(switchMap(() => {
      return userService.emailExists(control.value, id).pipe(map(
        result => {
          return (result === true) ? {'emailExists': true} : null;
        }
      ));
    }));
  };
}

@Directive({
  selector: '[appEmailExists][ngModel]',
  providers: [{
    provide: NG_ASYNC_VALIDATORS,
    useExisting: EmailExistsDirective,
    multi: true
  }]
})
export class EmailExistsDirective implements AsyncValidator, OnInit {

  private id: number;

  constructor(private userService: UserService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(
      params => {
        const id = +params.get('id');
        (id) ? this.id = id : this.id = 0 ;
      });
  }

  validate(control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return existingEmailValidator(this.userService, this.id)(control);
  }
}
