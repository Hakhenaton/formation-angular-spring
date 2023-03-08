import { Injectable } from "@angular/core";
import { HttpClient, HttpStatusCode } from '@angular/common/http'
import { BehaviorSubject, Observable } from "rxjs";
import { User } from "src/app/users/models/User";

type AuthenticationState = User|null

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

    private readonly authUrl = 'http://127.0.0.1:8080/auth'

    readonly authState$ = new BehaviorSubject<AuthenticationState>(null)

    constructor(private readonly http: HttpClient){}
    
    loadState(): void {
        this.http.get<User>(this.authUrl, {
            observe: 'response'
        })
            .subscribe({
                next: response => {

                    switch (response.status){
                        case HttpStatusCode.NoContent:
                            this.authState$.next(null)
                            break
                        case HttpStatusCode.Ok:
                            const user = new User(response.body!)
                            this.authState$.next(user)
                            break
                        default:

                    }

                },
                error: err => console.error(err)
            })
    }
}