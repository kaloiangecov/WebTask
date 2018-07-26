import { timer as observableTimer,  Subscription ,  Observable } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
import { Directive, Input, OnInit } from '@angular/core';
import { AsyncValidator, NG_ASYNC_VALIDATORS, AbstractControl,
         ValidationErrors, AsyncValidatorFn } from '@angular/forms';
import { AcademicsService } from '../../components/academics/academics.service';
import { ActivatedRoute, Router } from '@angular/router';


export function existingCodeValidator(academicsService: AcademicsService, id: number): AsyncValidatorFn {
  return (control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> => {
    const debounceTime = 500;
    return observableTimer(debounceTime).pipe(switchMap(() => {
      return academicsService.codeExists(control.value, id).pipe(map(
        result => {
          return (result === true) ? {'codeExists': true} : null;
        }
      ));
    }));
  };
}

@Directive({
  selector: '[appCodeExists][ngModel]',
  providers: [{
    provide: NG_ASYNC_VALIDATORS,
    useExisting: CodeExistsDirective,
    multi: true
  }]
})
export class CodeExistsDirective implements AsyncValidator, OnInit {

  private id: number;

  constructor(private academicsService: AcademicsService,
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
    return existingCodeValidator(this.academicsService, this.id)(control);
  }
}
