import { Injectable } from "@angular/core";
import { HttpClient, HttpStatusCode } from '@angular/common/http'
import { BehaviorSubject, map, Observable, tap } from "rxjs";
import { User } from "src/app/users/models/User";
import { UserResponseMapper } from "src/app/users/services/user-response.mapper"
import { UserResponse } from "src/app/users/services/user-response"
import { API_ORIGIN } from "src/app/apps.constants"

export type NonAuthenticatedState = {
    type: 'anonymous'
}

export type AuthenticatedState = {
    type: 'authenticated',
    user: User
}

export type AuthenticationState = AuthenticatedState|NonAuthenticatedState

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

    private readonly authUrl = `${API_ORIGIN}/auth`

    get authState$(): Observable<AuthenticationState> {
        return this._authState$
    }

    private readonly _authState$ = new BehaviorSubject<AuthenticationState>({ type: 'anonymous' })

    constructor(private readonly http: HttpClient, private readonly userResponseMapper: UserResponseMapper){}
    
    loadState(): Observable<void> {
        return this.http.get<UserResponse>(this.authUrl, {
            observe: 'response',
        }).pipe(
            tap(response => {
                switch (response.status){
                    case HttpStatusCode.NoContent:
                        this._authState$.next({ type: 'anonymous' })
                        break
                    case HttpStatusCode.Ok:
                        const user = this.userResponseMapper.map(response.body!)
                        this._authState$.next({ type: 'authenticated', user })
                        break
                    default:
                        throw Error(`fetch auth state failed (${response.status})`)
                }
            }),
            map(_response => undefined)
        )
    }

    authenticate(email: string, password: string): Observable<void> {
        return this.http.post<UserResponse>(this.authUrl, { email, password }, {
            observe: 'response'
        }).pipe(
            tap(response => {
                switch (response.status){
                    case HttpStatusCode.Unauthorized:
                        throw Error('bad credentials')
                    case HttpStatusCode.Ok:
                        const user = this.userResponseMapper.map(response.body!)
                        this._authState$.next({ type: 'authenticated', user })
                        break
                    default:
                        throw Error(`authentication failed (${response.status})`)
                }
            }),
            map(_response => undefined)
        )
    }
}