import { UserRole } from "./UserRole"

export type UserProps = {
    id?: string
    lastName: string
    firstName: string
    role: UserRole
    dateOfBirth: Date
    email: string
}

export class User {

    id?: string
    lastName: string
    firstName: string
    role: UserRole
    dateOfBirth: Date
    email: string

    constructor(props: UserProps){
        this.id = props.id
        this.lastName = props.lastName
        this.firstName = props.firstName
        this.dateOfBirth = props.dateOfBirth
        this.email = props.email
        this.role = props.role
    }
}