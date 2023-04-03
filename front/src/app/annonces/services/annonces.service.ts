import { HttpClient } from "@angular/common/http"
import { Injectable } from "@angular/core"
import { map, Observable } from "rxjs"
import { API_ORIGIN } from "src/app/apps.constants"
import { Annonce } from "../models/Annonce"
import { CreateAnnonceParams } from "../models/CreateAnnonceParams"
import { UpdateAnnonceParams } from "../models/UpdateAnnonceParams"
import { AnnonceResponse } from "./AnnonceResponse"

@Injectable({ providedIn: 'root' })
export class AnnoncesService {

    private readonly annoncesUrl = `${API_ORIGIN}/api/annonces`

    constructor(private readonly http: HttpClient){}

    create(params: CreateAnnonceParams): Observable<Annonce> {
        return this.http.post<AnnonceResponse>(
            this.annoncesUrl, 
            params
        )
            .pipe(
                map(annonceResponse => mapAnnonceResponse(annonceResponse))
            )
    } 

    update(annonce: Annonce, params: UpdateAnnonceParams): Observable<Annonce> {
        return this.http.patch<AnnonceResponse>(
            `${this.annoncesUrl}/${annonce.id}`, 
            params
        ).pipe(
            map(annonceResponse => mapAnnonceResponse(annonceResponse))
        )
    }

    delete(annonce: Annonce): Observable<void> {
        return this.http.delete<void>(`${this.annoncesUrl}/${annonce.id}`)
    }

    findById(id: string): Observable<Annonce> {
        return this.http.get<AnnonceResponse>(`${this.annoncesUrl}/${id}`)
            .pipe(map(annonceResponse => mapAnnonceResponse(annonceResponse)))
    }

    findAll(): Observable<Annonce[]> {
        return this.http.get<AnnonceResponse[]>(this.annoncesUrl)
            .pipe(map(annonceResponses => annonceResponses.map(response => mapAnnonceResponse(response))))
    }
}

function mapAnnonceResponse(response: AnnonceResponse): Annonce {
    return new Annonce({
        ...response,
        createdAt: new Date(response.createdAt),
        updatedAt: new Date(response.updatedAt),
    })
}