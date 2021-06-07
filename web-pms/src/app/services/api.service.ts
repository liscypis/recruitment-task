import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserData } from '../models/UserData';

const API_URL = 'http://localhost:8080/api/v1/';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  // initRide(request: UserData): Observable<any> {
  //   console.log(request)
  //   return this.http.post(API_URL + 'initialOrderRideByDispatcher', request);
  // }
}
