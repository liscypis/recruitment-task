import { UserData } from "./UserData";

export class MessageDetails {
    id!: string;
    subject!: string;
    message!: string;
    user!: UserData;
}