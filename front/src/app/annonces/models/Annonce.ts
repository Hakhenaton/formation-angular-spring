import { UpdateAnnonceParams } from "./UpdateAnnonceParams"

type AnnonceProps = Readonly<{
    id: string
    title: string
    content: string
    createdAt: Date
    updatedAt: Date
    authorId: string
}>

export class Annonce {

    public readonly id: string
    public readonly title: string
    public readonly content: string
    public readonly createdAt: Date
    public readonly updatedAt: Date
    public readonly authorId: string

    constructor(props: AnnonceProps){
        this.id = props.id
        this.title = props.title
        this.content = props.content
        this.createdAt = props.createdAt
        this.updatedAt = props.updatedAt
        this.authorId = props.authorId
    }


}