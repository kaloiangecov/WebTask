import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SharedCoreModule } from '../../core/shared-core.module';
import { FacultyRoutingModule } from './faculty-routing.module';
import { FacultyListComponent } from './faculty-list/faculty-list.component';
import { FacultyCreateComponent } from './faculty-create/faculty-create.component';
import { FacultyViewComponent } from './faculty-view/faculty-view.component';


@NgModule({
  imports: [
    CommonModule,
    FacultyRoutingModule,
    SharedCoreModule,
    FormsModule
  ],
  declarations: [FacultyListComponent, FacultyCreateComponent, FacultyViewComponent]
})
export class FacultyModule { }
