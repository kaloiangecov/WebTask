import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Ng2CompleterModule } from 'ng2-completer';
import { CommonModule } from '@angular/common';
import { SharedCoreModule } from '../../core/shared-core.module';
import { DisciplineRoutingModule } from './discipline-routing.module';
import { DisciplineViewComponent } from './discipline-view/discipline-view.component';
import { DisciplineListComponent } from './discipline-list/discipline-list.component';
import { DisciplineCreateComponent } from './discipline-create/discipline-create.component';
import { DisciplineSemesterFilterPipe } from './discipline-semester-filter.pipe';

@NgModule({
  imports: [
    CommonModule,
    DisciplineRoutingModule,
    SharedCoreModule,
    FormsModule,
    Ng2CompleterModule
  ],
  declarations: [DisciplineViewComponent, DisciplineListComponent,
                 DisciplineCreateComponent, DisciplineSemesterFilterPipe]
})
export class DisciplineModule { }
