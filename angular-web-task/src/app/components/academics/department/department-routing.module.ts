import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DepartmentListComponent } from './department-list/department-list.component';
import { DepartmentCreateComponent } from './department-create/department-create.component';
import { DepartmentViewComponent } from './department-view/department-view.component';
import {
  AuthGuardService as AuthGuard
} from '../../../shared/authentication/guards/auth-guard.service';
import { AdminGuardService as AdminGuard } from '../../../shared/authentication/guards/admin-guard.service';

const routes: Routes = [
  {
    path: 'departments',
    component: DepartmentListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'departments/details/:id',
    component: DepartmentViewComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'departments/create',
    component: DepartmentCreateComponent,
    canActivate: [AuthGuard, AdminGuard]
  },
  {
    path: 'departments/edit/:id',
    component: DepartmentCreateComponent,
    canActivate: [AuthGuard, AdminGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DepartmentRoutingModule { }
