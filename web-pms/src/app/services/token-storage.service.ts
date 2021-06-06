import { Injectable } from '@angular/core';
import { UserDetails } from '../models/UserDetails';

const TOKEN_KEY = 'token';
const USER_KEY = 'user-details';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut(): void {
    window.sessionStorage.clear();
  }

  public saveJWTToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getJWTToken(): string | null{
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUserDetails(user: UserDetails): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUserDetails(): any  {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return {};
  }
}
