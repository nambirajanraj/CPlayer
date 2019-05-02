import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './modules/authentication/authentication.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'CPlayerUI';
  ishome:boolean;
  constructor(private authService: AuthenticationService,
    private router: Router) {
      this.ishome=true;
     }

  logout() {
    this.authService.deleteToken();
    this.router.navigate(['']);
  }

}
