import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { UserRoutingModule } from './user-routing.module';
import { SharedCoreModule } from '../components/core/shared-core.module';

import { UserListComponent } from './user-list/user-list.component';
import { UserCreateComponent } from './user-create/user-create.component';


@NgModule({
  imports: [
    CommonModule,
    UserRoutingModule,
    FormsModule,
    NgbModule,
    SharedCoreModule
  ],
  declarations: [
    UserListComponent,
    UserCreateComponent,
  ]
})
export class UserModule { }
