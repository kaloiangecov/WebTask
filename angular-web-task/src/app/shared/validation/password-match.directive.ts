import { Directive } from '@angular/core';
import { Validator, NG_VALIDATORS, AbstractControl } from '@angular/forms';


@Directive({
  selector: '[appPasswordMatch][ngModel]',
  providers: [{
    provide: NG_VALIDATORS,
    useExisting: PasswordMatchDirective,
    multi: true
  }]
})

export class PasswordMatchDirective implements Validator {

  constructor() { }

  validate(control: AbstractControl): {[key: string]: any} | null {
    const password = control.parent.get('password');
    const confirmPassword = control.value;
    return (password && password.value !== confirmPassword) ? {'passwordMissmatch': true} : null;
  }
}
