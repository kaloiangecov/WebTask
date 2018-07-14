import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SharedCoreModule } from '../../core/shared-core.module';
import { DepartmentRoutingModule } from './department-routing.module';
import { DepartmentCreateComponent } from './department-create/department-create.component';
import { DepartmentViewComponent } from './department-view/department-view.component';
import { DepartmentListComponent } from './department-list/department-list.component';


@NgModule({
  imports: [
    CommonModule,
    DepartmentRoutingModule,
    SharedCoreModule,
    FormsModule
  ],
  declarations: [DepartmentCreateComponent, DepartmentViewComponent, DepartmentListComponent]
})
export class DepartmentModule { }
