import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserData } from 'src/app/models/UserData';
import { UserPasswordRequest } from 'src/app/models/UserPasswordRequest';
import { ApiService } from 'src/app/services/api.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-edit-user-data',
  templateUrl: './edit-user-data.component.html',
  styleUrls: ['./edit-user-data.component.css']
})
export class EditUserDataComponent implements OnInit {

  successPassword!: string;
  successUser!: string;
  passError!: string;
  numberError!: string;
  emailError!: string;
  usernameError!: string;


  editPassError!: string;

  updateForm = new FormGroup({
    username: new FormControl('', [Validators.minLength(4), Validators.required]),
    password: new FormControl('', [Validators.minLength(4), Validators.required]),
    email: new FormControl('', [Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"), Validators.minLength(7), Validators.required]),
    phoneNumber: new FormControl('', [Validators.pattern("[0-9]+"), Validators.minLength(9), Validators.maxLength(9), Validators.required]),
  });

  updatePassForm = new FormGroup({
    pass: new FormControl('', [Validators.minLength(4), Validators.required]),
    pass2: new FormControl('', [Validators.minLength(4), Validators.required]),

  });

  constructor(private api: ApiService,
    private storage: TokenStorageService,
    private router: Router) { }

  ngOnInit(): void {
    let userData = new UserData()
    let id = this.storage.getUserDetails().id;
    this.api.getUser(id).subscribe(response => {
      userData = response;
      console.log(userData);
      this.updateUserForm(userData);
    },
      error => {
        console.log(error);
      });
  }

  updateUserForm(data: UserData): void {
    this.updateForm.controls['username'].setValue(data.username);
    this.updateForm.controls['email'].setValue(data.email);
    this.updateForm.controls['phoneNumber'].setValue(data.phoneNumber);
  }


  updateUser(): void {
    this.successUser = "";
    let userData = new UserData();
    userData.email = this.updateForm.value.email;
    userData.password = this.updateForm.value.password;
    userData.phoneNumber = this.updateForm.value.phoneNumber;
    userData.username = this.updateForm.value.username;
    userData.role = "ROLE_USER";

    this.api.updateUser(userData, this.storage.getUserDetails().id).subscribe(() => {
      this.successUser = "Zapisano";
      if (userData.username != this.storage.getUserDetails().username) {
        this.router.navigate(["/logout"], { state: { info: 'Zmieniono login, zaloguj się ponownie' } });
      }
    },
      err => {
        console.log(err.error.message);
        this.checkError(err.error.message);
      })
  }

  updatePasswordUser(): void {
    let newPass = new UserPasswordRequest();
    newPass.oldPassword = this.updatePassForm.value.pass;
    newPass.newPassword = this.updatePassForm.value.pass2;
    newPass.id = this.storage.getUserDetails().id;

    this.successPassword = "";
    this.api.updateUserPassword(newPass)
      .subscribe(() => {
        this.successPassword = "Zapisano"
      },
        err => {
          console.log(err);
          if (err.error.message == "Password not matches")
            this.editPassError = "Obecne hasło nieprawidłowe"
          this.updatePassForm.get('pass')!.setErrors({ valid: false });

        })

  }


  checkError(msg: string): void {
    if (msg == "Password not matches") {
      this.passError = "Błędne hasło"
      this.updateForm.get('password')!.setErrors({ valid: false });
    }
    if (msg == "Username is taken") {
      this.usernameError = "Login jest zajęty"
      this.updateForm.get('username')!.setErrors({ valid: false });
    }
    if (msg == "Email is taken") {
      this.emailError = "Email jest zajęty"
      this.updateForm.get('email')!.setErrors({ valid: false });
    }
    if (msg == "phone number is taken") {
      this.numberError = "Numer telefonu jest zajęty"
      this.updateForm.get('phoneNumber')!.setErrors({ valid: false });
    }

  }




}
