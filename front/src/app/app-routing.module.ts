import { NgModule } from "@angular/core"
import { Route, RouterModule, Routes } from "@angular/router"
import { LoginComponent } from "./authentication/components/login.component"

const routes: Routes = [
 {
    path: 'login',
    component: LoginComponent
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