import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { AccordionModule } from 'ngx-bootstrap/accordion';
import { UsernameExistsDirective } from '../../shared/validation/username-exists.directive';
import { EmailExistsDirective } from '../../shared/validation/email-exists.directive';
import { CodeExistsDirective } from '../../shared/validation/code-exists.directive';
import { PasswordMatchDirective } from '../../shared/validation/password-match.directive';
import { DropdownRequiredDirective } from '../../shared/validation/dropdown-required.directive';

@NgModule({
  imports: [
    CommonModule,
    NgbModule,
    BsDropdownModule.forRoot(),
    AccordionModule.forRoot(),
  ],
  declarations: [
    UsernameExistsDirective,
    EmailExistsDirective,
    CodeExistsDirective,
    PasswordMatchDirective,
    DropdownRequiredDirective
  ],
  exports: [
    NgbModule,
    BsDropdownModule,
    AccordionModule,
    UsernameExistsDirective,
    EmailExistsDirective,
    CodeExistsDirective,
    PasswordMatchDirective,
    DropdownRequiredDirective
  ]
})
export class SharedCoreModule { }
