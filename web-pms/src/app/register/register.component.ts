import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserData } from '../models/UserData';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm = new FormGroup({
    username: new FormControl('', [Validators.minLength(4), Validators.required]),
    password: new FormControl('', [Validators.minLength(4), Validators.required]),
    password2: new FormControl('', [Validators.minLength(4), Validators.required]),
    email: new FormControl('', [Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"), Validators.minLength(7), Validators.required]),
    phoneNumber: new FormControl('', [Validators.pattern("[0-9]+"), Validators.minLength(9), Validators.maxLength(9), Validators.required]),
  });
  errorMessage: string = '';

  constructor(private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {


  }

  registerUser(): void {
    this.errorMessage = '';
    if (this.checkPassword()) {
      let userData = new UserData();
      userData.email = this.registerForm.value.email;
      userData.password = this.registerForm.value.password;
      userData.phoneNumber = this.registerForm.value.phoneNumber;
      userData.username = this.registerForm.value.username;
      userData.role = "ROLE_USER";

      this.authService.register(userData).subscribe(() => {
        this.router.navigate(["/login"])
      },
        err => {
          this.checkError(err.error.message);
          console.log(err);
        }
      );
    }
  }

  checkPassword(): boolean {
    if (this.registerForm.value.password != this.registerForm.value.password2) {
      this.errorMessage = "Hasła nie są takie same";
      this.registerForm.controls.password.setErrors({ 'error': true });
      this.registerForm.controls.password2.setErrors({ 'error': true });
      return false;
    }
    return true;
  }

  checkError(msg: string): void {
    if (msg == "The username is already in use.")
      this.errorMessage = "Login jest zajęty";
    if (msg == "The email is already in use.")
      this.errorMessage = "Email jest zajęty";
    if (msg == "The phone number is already in use.")
      this.errorMessage = "Numer telefonu jest zajęty";
  }
}
