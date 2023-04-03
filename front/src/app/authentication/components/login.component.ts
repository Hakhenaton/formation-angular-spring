import { Component, OnInit } from "@angular/core"
import { FormBuilder, FormControl, FormGroup } from "@angular/forms"
import { Router } from "@angular/router"
import { AuthenticationService } from "../services/authentication.service"

@Component({
    selector: 'app-login',
    styles: [],
    template: `
        <div *ngIf="error">{{ error.message }}</div>
        <form [formGroup]="form" (ngSubmit)="authenticate()">
            <label for="email">Email</label>
            <input formControlName="email" id="email" type="text" placeholder="toto@sncf.fr">
            <label for="password">Password</label>
            <input formControlName="password" id="password" type="password">
            <button type="submit">Se connecter</button>
        </form>
    `
})
export class LoginComponent implements OnInit {

    form!: FormGroup

    error?: Error

    constructor(
        private readonly authenticationService: AuthenticationService, 
        private readonly formBuilder: FormBuilder,
        private readonly router: Router
    ){}
    
    
    ngOnInit(): void {
        this.form = this.formBuilder.group({
            email: this.formBuilder.control(''),
            password: this.formBuilder.control('')
        })
    }

    authenticate(): void {
        const { password, email } = this.form.value
        this.authenticationService.authenticate(email, password)
            .subscribe({
                next: () => {
                    this.router.navigateByUrl('/')
                },
                error: err => {
                    this.error = err
                }
            })
    }
}