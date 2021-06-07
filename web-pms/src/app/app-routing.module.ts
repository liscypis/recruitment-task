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
import { RoleGuardService } from './services/role-guard.service';
import { EditUserDataComponent } from './user/edit-user-data/edit-user-data.component';
import { EditUsersDataComponent } from './user/edit-users-data/edit-users-data.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'register', component: RegisterComponent },
  
  {
    path: 'products', 
    component: ProductsComponent, 
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_USER'
    }
  },
  {
    path: 'editProducts', 
    component: EditProductsComponent, 
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_ADMIN'
    }
  },
  {
    path: 'addProduct', 
    component: AddProductsComponent, 
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_ADMIN'
    }
  },
  {
    path: 'editCategory', 
    component: EditCategoryComponent, 
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_ADMIN'
    }
  },
  {
    path: 'addCategory', 
    component: AddCategoryComponent, 
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_ADMIN'
    }
  },
  {
    path: 'editUser', 
    component: EditUserDataComponent, 
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_USER'
    }
  },
  {
    path: 'editUsers', 
    component: EditUsersDataComponent, 
    canActivate: [RoleGuardService],
    data: {
      expectedRole: 'ROLE_ADMIN'
    }
  },
  { path: '**', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
