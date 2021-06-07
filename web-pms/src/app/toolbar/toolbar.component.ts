import { Component, OnInit } from '@angular/core';
import { UserDetails } from '../models/UserDetails';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {
  private roles!: string[];
  isLoggedIn = false;
  admin = false;
  user = false;
  username! :string


  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getJWTToken();

    if (this.isLoggedIn) {
      const userDetails: UserDetails = this.tokenStorageService.getUserDetails();
      this.roles = userDetails.role;

      this.admin = this.roles.includes('ROLE_ADMIN');
      this.user = this.roles.includes('ROLE_USER');

      this.username = userDetails.username;
      console.log(userDetails);
    }
  }

}
