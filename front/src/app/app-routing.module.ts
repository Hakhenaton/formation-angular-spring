import { NgModule } from "@angular/core"
import { Route, RouterModule, Routes } from "@angular/router"
import { CreateAnnonceComponent } from "./annonces/components/create-annonce.component"
import { LoginComponent } from "./authentication/components/login.component"

const routes: Routes = [
 {
    path: 'login',
    component: LoginComponent
 },
 {
    path: 'annonces',
    children: [
        {
            path: 'create',
            component: CreateAnnonceComponent
        }
    ]
 }
]

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule {}