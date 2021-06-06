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
    this.loginData = new UserLogin();
    this.loginForm = this.formBuilder.group({
      username: new FormControl('', [
        Validators.minLength(4),
        Validators.required
      ]),
      password: new FormControl('',[
        Validators.minLength(4),
        Validators.required
      ])
    })
  }
  onSubmit(): void {
    this.errorMessage ='';
    let userDetails = new UserDetails();
    this.authService.login(this.loginData).subscribe(result =>{
      userDetails = result;
      this.tokenStorageService.saveJWTToken(userDetails.jwt);
      this.tokenStorageService.saveUserDetails(userDetails);
      // this.router.navigate(["/##"])
      console.log(userDetails);
    },
    err => {
      this.errorMessage = err.error;
      console.log(err);
    }
    );
  }

}
