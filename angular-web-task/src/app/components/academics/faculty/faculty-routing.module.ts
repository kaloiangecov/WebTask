import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FacultyListComponent } from './faculty-list/faculty-list.component';
import { FacultyCreateComponent } from './faculty-create/faculty-create.component';
import { FacultyViewComponent } from './faculty-view/faculty-view.component';
import {
  AuthGuardService as AuthGuard
} from '../../../shared/authentication/guards/auth-guard.service';
import { AdminGuardService as AdminGuard } from '../../../shared/authentication/guards/admin-guard.service';

const routes: Routes = [
  {
    path: 'faculties',
    component: FacultyListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'faculties/details/:id',
    component: FacultyViewComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'faculties/create',
    component: FacultyCreateComponent,
    canActivate: [AuthGuard, AdminGuard]
  },
  {
    path: 'faculties/edit/:id',
    component: FacultyCreateComponent,
    canActivate: [AuthGuard, AdminGuard]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FacultyRoutingModule { }
