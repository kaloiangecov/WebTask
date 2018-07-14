import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SharedCoreModule } from '../../core/shared-core.module';
import { SubjectRoutingModule } from './subject-routing.module';
import { SubjectCreateComponent } from './subject-create/subject-create.component';
import { SubjectListComponent } from './subject-list/subject-list.component';
import { SubjectViewComponent } from './subject-view/subject-view.component';


@NgModule({
  imports: [
    CommonModule,
    SubjectRoutingModule,
    FormsModule,
    SharedCoreModule
  ],
  declarations: [SubjectCreateComponent, SubjectListComponent, SubjectViewComponent]
})
export class SubjectModule { }
