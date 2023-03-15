import { Component, OnInit } from '@angular/core';
import { map, Observable } from 'rxjs'
import { AuthenticationService, AuthenticationState } from './authentication/services/authentication.service';
import { User } from './users/models/User'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  readonly auth$: Observable<AuthenticationState>

  constructor(authenticationService: AuthenticationService){
    this.auth$ = authenticationService.authState$
  }

}
