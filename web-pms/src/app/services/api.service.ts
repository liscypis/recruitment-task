import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/Category';
import { MessageRequest } from '../models/MessageRequest';
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

  updateUserByAdmin(user: UserData): Observable<any> {
    return this.http.put(API_URL + 'userByAdmin', user)
  }
  updateUserPassword(pass: UserPasswordRequest): Observable<any> {
    return this.http.patch(API_URL + 'user', pass);
  }
  
  updateUserPasswordByAdmin(pass: UserPasswordRequest): Observable<any> {
    return this.http.patch(API_URL + 'userPasswordByAdmin', pass);
  }

  deleteUser(id:string): Observable<any> {
    return this.http.delete(API_URL + 'user/' + id);
  }

  getAvailableProducts() :Observable<any> {
    return this.http.get(API_URL + 'availableProducts');
  }

  getProducts() :Observable<any> {
    return this.http.get(API_URL + 'products');
  }
  searchAvailableProducts(name: string):Observable<any> {
    return this.http.get(API_URL + 'searchProducts/'+ name)
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
  addProduct(product: ProductDetails, categoryId:string) :Observable<any> {
    return this.http.post(API_URL + 'product',product, { params: { categoryId: categoryId } })
  }
  addCategory(category: Category): Observable<any> {
    return this.http.post(API_URL + 'category', category);
  }

  updateCategory(category: Category, categoryId: string): Observable<any> {
    return this.http.put(API_URL + 'category', category, { params: { id: categoryId }});
  }

  deleteCategory(categoryId:string) :Observable<any> {
    return this.http.delete(API_URL + 'category/' + categoryId);
  }
  getUsers():Observable<any> {
    return this.http.get(API_URL + 'users');
  }
  getMessages():Observable<any> {
    return this.http.get(API_URL + 'messages');
  }
  sendMessage(message:MessageRequest):Observable<any> {
    return this.http.post(API_URL + 'message', message);
  }

}
