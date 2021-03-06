import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { ProductsComponent } from './products/products.component';
import {MatMenuModule} from '@angular/material/menu';
import { LogoutComponent } from './logout/logout.component';
import { EditProductsComponent } from './products/edit-products/edit-products.component';
import { AddProductsComponent } from './products/add-products/add-products.component';
import { AddCategoryComponent } from './category/add-category/add-category.component';
import { EditCategoryComponent } from './category/edit-category/edit-category.component';
import { EditUserDataComponent } from './user/edit-user-data/edit-user-data.component';
import { EditUsersDataComponent } from './user/edit-users-data/edit-users-data.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { authInterceptorProviders } from './services/interceptor.service';
import {MatTableModule} from '@angular/material/table';
import {ScrollingModule} from '@angular/cdk/scrolling';
import { MatSortModule } from '@angular/material/sort';
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';
import { UserMessageComponent } from './message/user-message/user-message.component';
import { AdminMessageComponent } from './message/admin-message/admin-message.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    ToolbarComponent,
    ProductsComponent,
    LogoutComponent,
    EditProductsComponent,
    AddProductsComponent,
    AddCategoryComponent,
    EditCategoryComponent,
    EditUserDataComponent,
    EditUsersDataComponent,
    UserMessageComponent,
    AdminMessageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatInputModule,
    MatButtonModule,
    MatMenuModule,
    MatGridListModule,
    MatTableModule,
    ScrollingModule,
    MatSortModule,
    MatIconModule,
    MatSelectModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
