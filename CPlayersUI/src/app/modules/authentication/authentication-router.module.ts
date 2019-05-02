import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';

import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';

const authRouter: Routes = [
  {
    path:'login',
    component: LoginComponent
    
  
  },
  {
    path:'register',
    component:RegisterComponent
  }

];

@NgModule({
  imports: [
    RouterModule.forChild(authRouter)
  ],
  exports: [
    RouterModule
  ]
})
export class AuthenticationRouterModule { }
