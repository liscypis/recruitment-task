import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserLogin } from '../models/UserLogin';
import { UserData } from '../models/UserData';


const AUTH_API = 'http://localhost:8080/api/v1/auth/';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }


  login(request: UserLogin): Observable<any> {
    console.log(request)
    return this.http.post(AUTH_API + 'login', request);
  }

  register(request: UserData): Observable<any> {
    console.log(request)
    return this.http.post(AUTH_API + 'register', request);
  }
}
