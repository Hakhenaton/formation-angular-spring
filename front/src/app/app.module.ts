import { HttpClientModule } from '@angular/common/http'
import { APP_INITIALIZER, NgModule } from '@angular/core'
import { ReactiveFormsModule } from '@angular/forms'
import { BrowserModule } from '@angular/platform-browser'
import { Observable } from 'rxjs'
import { AppRoutingModule } from './app-routing.module'

import { AppComponent } from './app.component'
import { LoginComponent } from './authentication/components/login.component'
import { AuthenticationService } from './authentication/services/authentication.service'

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: (authService: AuthenticationService) => () => authService.loadState(),
      multi: true,
      deps: [AuthenticationService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
