import { Injectable } from "@angular/core"
import { User } from "../models/User"
import { UserRole } from "../models/UserRole"
import { UserResponse } from "./user-response"

@Injectable({ providedIn: 'root' })
export class UserResponseMapper {

    map(userResponse: UserResponse): User {
        return new User({
            ...userResponse,
            dateOfBirth: new Date(userResponse.dateOfBirth),
            role: userResponse.role as UserRole
        })
    }
}