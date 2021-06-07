import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserData } from '../models/UserData';

const API_URL = 'http://localhost:8080/api/v1/';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getUser(userId: string): Observable<any> {
    return this.http.get(API_URL + 'user/' + userId);
  }

  updateUser(user: UserData, id: string): Observable<any> {
    return this.http.put(API_URL + 'user', user, { params: { userid: id } })
  }
}
