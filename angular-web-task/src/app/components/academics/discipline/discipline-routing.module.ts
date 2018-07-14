import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DisciplineListComponent } from './discipline-list/discipline-list.component';
import { DisciplineCreateComponent } from './discipline-create/discipline-create.component';
import { DisciplineViewComponent } from './discipline-view/discipline-view.component';
import {
  AuthGuardService as AuthGuard
} from '../../../shared/authentication/guards/auth-guard.service';
import { AdminGuardService as AdminGuard } from '../../../shared/authentication/guards/admin-guard.service';

const routes: Routes = [
  {
    path: 'disciplines',
    component: DisciplineListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'disciplines/details/:id',
    component: DisciplineViewComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'disciplines/create',
    component: DisciplineCreateComponent,
    canActivate: [AuthGuard, AdminGuard]
  },
  {
    path: 'disciplines/edit/:id',
    component: DisciplineCreateComponent,
    canActivate: [AuthGuard, AdminGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DisciplineRoutingModule { }
