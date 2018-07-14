import { Directive } from '@angular/core';
import { Validator, NG_VALIDATORS, AbstractControl } from '@angular/forms';


@Directive({
  selector: '[appDropdownRequired][ngModel]',
  providers: [{
    provide: NG_VALIDATORS,
    useExisting: DropdownRequiredDirective,
    multi: true
  }]
})

export class DropdownRequiredDirective implements Validator {

  constructor() { }

  validate(control: AbstractControl): {[key: string]: any} | null {
    const selector = control.value;

    return (selector === -1 || selector == null) ? {'nothingSelected': true} : null;
  }
}
