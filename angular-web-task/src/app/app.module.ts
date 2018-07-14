import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ResizableModule } from 'angular-resizable-element';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserModule } from './user/user.module';
import { CoreModule } from './components/core/core.module';
import { SharedCoreModule } from './components/core/shared-core.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Interceptor } from './shared/authentication/app.interceptor';

import { AuthService } from './shared/authentication/auth.service';
import { TokenStorage } from './shared/authentication/token.storage';
import { UserService } from './user/user.service';
import { RoleService } from './user/role.service';
import {
  AuthGuardService as AuthGuard
} from './shared/authentication/guards/auth-guard.service';
import {
  EditPersonalGuardService as EditGuard
} from './shared/authentication/guards/edit-personal-guard.service';
import { AdminGuardService as AdminGuard } from './shared/authentication/guards/admin-guard.service';
import { AcademicsModule } from './components/academics/academics.module';


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    UserModule,
    AcademicsModule.forRoot(),
    CoreModule,
    SharedCoreModule,
    FormsModule,
    HttpClientModule,
    ResizableModule,
    NgbModule.forRoot()
  ],
  providers: [UserService, RoleService, AuthService, TokenStorage, AuthGuard, EditGuard,
              AdminGuard,
              { provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
