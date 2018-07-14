import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component';
import { UserCreateComponent } from './user-create/user-create.component';
import {
  AuthGuardService as AuthGuard
} from '../shared/authentication/guards/auth-guard.service';
import {
  EditPersonalGuardService as EditGuard
} from '../shared/authentication/guards/edit-personal-guard.service';
import { AdminGuardService as AdminGuard } from '../shared/authentication/guards/admin-guard.service';


const routes: Routes = [
  {
    path: 'users',
    component: UserListComponent,
    canActivate: [AuthGuard, AdminGuard]
  },
  {
    path: 'users/create',
    component: UserCreateComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'users/edit/:id',
    component: UserCreateComponent,
    canActivate: [AuthGuard, EditGuard]
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
