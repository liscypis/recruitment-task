import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { UserDetails } from '../models/UserDetails';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService implements CanActivate {

  constructor(private router: Router, private tokenService: TokenStorageService) { }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    // this will be passed from the route config
    // on the data property
    const expectedRole = route.data.expectedRole;

   
    if (!!this.tokenService.getJWTToken()) {
      let userDetails: UserDetails = this.tokenService.getUserDetails();
      let role = userDetails.role;
      
      if (!role.includes(expectedRole)) {
        this.router.navigate(["/login"])
        return false;
      }
    } else {
      this.router.navigate(["/login"])
      return false;
    }

    return true;
  }
}
