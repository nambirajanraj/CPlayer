import { NgModule,CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { SharedModule } from '../shared/shared.module';
import { FormsModule } from '@angular/forms';
import { AuthenticationRouterModule } from '../authentication/authentication-router.module';
import { AuthenticationService } from '../authentication/authentication.service';
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    LoginComponent, 
    RegisterComponent, HomeComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    AuthenticationRouterModule
  ],
  providers: [AuthenticationService],
  exports: [
    AuthenticationRouterModule,RegisterComponent,LoginComponent
  ]
})
export class AuthenticationModule { }

