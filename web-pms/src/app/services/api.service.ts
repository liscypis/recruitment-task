import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductDetails } from '../models/ProductDetails';
import { UserData } from '../models/UserData';
import { UserPasswordRequest } from '../models/UserPasswordRequest';

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
  updateUserPassword(pass: UserPasswordRequest): Observable<any> {
    return this.http.patch(API_URL + 'user', pass)
  }

  getAvailableProducts() :Observable<any> {
    return this.http.get(API_URL + 'availableProducts');
  }

  getProducts() :Observable<any> {
    return this.http.get(API_URL + 'products');
  }

  getCategories() :Observable<any> {
    return this.http.get(API_URL + 'categories');
  }

  updateProduct(product: ProductDetails, productId: string, categoryId: string): Observable<any> {
    return this.http.put(API_URL + 'product', product, { params: { productId: productId, categoryId: categoryId } })
  }

  deleteProduct(id: string): Observable<any> {
    return this.http.delete(API_URL + 'product/' +id );
  }

}
