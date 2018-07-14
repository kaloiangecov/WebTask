import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CoreRoutingModule } from './core-routing.module';
import { SharedCoreModule } from './shared-core.module';

import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    CoreRoutingModule,
    SharedCoreModule
  ],
  declarations: [
    LoginComponent,
    HomeComponent,
    RegisterComponent,
  ]
})
export class CoreModule { }
