import { Component, OnInit } from "@angular/core"
import { FormBuilder, FormGroup, Validators } from "@angular/forms"
import { Router } from "@angular/router"
import { switchMap, take } from "rxjs"
import { AuthenticationService } from "src/app/authentication/services/authentication.service"
import { AnnoncesService } from "../services/annonces.service"

@Component({
    selector: 'app-create-annonce',
    template: `
        <form [formGroup]="form" (ngSubmit)="submit()">
            <div *ngIf="error">{{ error.message }}</div>
            <label for="title">Titre de l'annonce (requis)</label>
            <input type="text" id="title" formControlName="title">
            <label for="content">Contenu (requis)</label>
            <textarea id="content" formControlName="content"></textarea>
            <button type="submit">Envoyer l'annonce</button>
        </form>
    `,
    styles: []
})
export class CreateAnnonceComponent implements OnInit {

    form!: FormGroup

    error?: Error

    constructor(
        private readonly fb: FormBuilder, 
        private readonly annoncesService: AnnoncesService, 
        private readonly authenticationService: AuthenticationService,
        private readonly router: Router
    ){}

    ngOnInit(): void {
        this.form = this.fb.group({
            title: this.fb.control('', Validators.required),
            content: this.fb.control('', Validators.required)
        })
    }

    submit(): void {
        if (this.form.invalid){
            alert('formulaire invalide !')
            return
        }           

        this.authenticationService.authState$
            .pipe(
                take(1),
                switchMap(authentication => {

                    if (authentication.type !== 'authenticated'){
                        throw Error('user is not authenticated')
                    }

                    return this.annoncesService.create({
                        ...this.form.value,
                        authorId: authentication.user.id!
                    })
                })
            )
            .subscribe({
                next: annonce => {
                    delete this.error
                    console.log(annonce)
                },
                error: err => {
                    this.error = err
                }
            })
    }
}