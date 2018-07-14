import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SubjectListComponent } from './subject-list/subject-list.component';
import { SubjectCreateComponent } from './subject-create/subject-create.component';
import { SubjectViewComponent } from './subject-view/subject-view.component';
import {
  AuthGuardService as AuthGuard
} from '../../../shared/authentication/guards/auth-guard.service';
import { AdminGuardService as AdminGuard } from '../../../shared/authentication/guards/admin-guard.service';

const routes: Routes = [
  {
    path: 'subjects',
    component: SubjectListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'subjects/details/:id',
    component: SubjectViewComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'subjects/create',
    component: SubjectCreateComponent,
    canActivate: [AuthGuard, AdminGuard]
  },
  {
    path: 'subjects/edit/:id',
    component: SubjectCreateComponent,
    canActivate: [AuthGuard, AdminGuard]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SubjectRoutingModule { }
