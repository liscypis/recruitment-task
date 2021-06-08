import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
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

  updateMessage!: string;
  editPassMessage!: string;
  successPassword!: string;
  successUser!: string;

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
    private storage: TokenStorageService) { }

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
    this.updateMessage = "";
    this.successUser = "";
    let userData = new UserData();
    userData.email = this.updateForm.value.email;
    userData.password = this.updateForm.value.password;
    userData.phoneNumber = this.updateForm.value.phoneNumber;
    userData.username = this.updateForm.value.username;
    userData.role = "ROLE_USER";

    this.api.updateUser(userData, this.storage.getUserDetails().id).subscribe(() => {
      this.successUser = "Zapisano";
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
    this.editPassMessage = "";
    this.api.updateUserPassword(newPass)
    .subscribe(()=>{
      this.successPassword = "Zapisano"
    },
    err =>{
      console.log(err);
      if(err.error.message == "Password not matches")
      this.editPassMessage = "Obecne hasło nieprawidłowe"
    })

  }


  checkError(msg: string): void {
    if (msg == "Password not matches")
      this.updateMessage = "Błędne hasło";
    if (msg == "Username is taken")
      this.updateMessage = "Login jest zajęty";
    if (msg == "Email is taken")
      this.updateMessage = "Email jest zajęty";
      if (msg == "phone number is taken")
      this.updateMessage = "Numer telefonu jest zajęty";
  }
  


}
