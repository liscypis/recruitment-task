import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCategoryComponent } from './category/add-category/add-category.component';
import { EditCategoryComponent } from './category/edit-category/edit-category.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AddProductsComponent } from './products/add-products/add-products.component';
import { EditProductsComponent } from './products/edit-products/edit-products.component';
import { ProductsComponent } from './products/products.component';
import { RegisterComponent } from './register/register.component';
import { EditUserDataComponent } from './user/edit-user-data/edit-user-data.component';
import { EditUsersDataComponent } from './user/edit-users-data/edit-users-data.component';

const routes: Routes = [
  {path: 'login', component:LoginComponent},
  {path: 'products', component:ProductsComponent},
  {path: 'logout', component:LogoutComponent},
  {path: 'editProducts', component:EditProductsComponent},
  {path: 'addProduct', component:AddProductsComponent},
  {path: 'editCategory', component:EditCategoryComponent},
  {path: 'addCategory', component:AddCategoryComponent},
  {path: 'editUser', component:EditUserDataComponent},
  {path: 'editUsers', component:EditUsersDataComponent},
  {path: 'register', component:RegisterComponent},

  {path: '', redirectTo: '/login', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
