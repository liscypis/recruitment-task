import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserDetails } from '../models/UserDetails';
import { UserLogin } from '../models/UserLogin';
import { AuthService } from '../services/auth.service';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData!: UserLogin;
  errorMessage = '';
  loginForm!: FormGroup;
  errorMessages: string = '';

  constructor(private tokenStorageService: TokenStorageService,
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    if (this.tokenStorageService.getJWTToken())
      this.loadPage();

    this.loginData = new UserLogin();
    this.loginForm = this.formBuilder.group({
      username: new FormControl('', [
        Validators.minLength(4),
        Validators.required
      ]),
      password: new FormControl('', [
        Validators.minLength(4),
        Validators.required
      ])
    })
  }
  onSubmit(): void {
    this.errorMessage = '';
    let userDetails = new UserDetails();
    this.authService.login(this.loginData).subscribe(result => {
      userDetails = result;
      this.tokenStorageService.saveJWTToken(userDetails.jwt);
      this.tokenStorageService.saveUserDetails(userDetails);
      console.log(userDetails);
      this.reloadPage();
    },
      err => {
        this.errorMessage = err.error;
        console.log(err);
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }

  loadPage(): void {
    const userDetails: UserDetails = this.tokenStorageService.getUserDetails();
    let roles = userDetails.role;
    if (roles.includes("ROLE_ADMIN"))
      this.router.navigate(["/editProducts"])
    if (roles.includes("ROLE_USER"))
      this.router.navigate(["/products"])

  }

}
