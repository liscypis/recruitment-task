import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { UserData } from 'src/app/models/UserData';
import { UserPasswordRequest } from 'src/app/models/UserPasswordRequest';
import { ApiService } from 'src/app/services/api.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';



@Component({
  selector: 'app-edit-users-data',
  templateUrl: './edit-users-data.component.html',
  styleUrls: ['./edit-users-data.component.css']
})
export class EditUsersDataComponent implements OnInit {


  displayedColumns: string[] = ['id', 'username', 'email', 'phoneNumber', 'role', 'edit'];
  dataSource!: MatTableDataSource<UserData>;


  updatePasswordForm = new FormGroup({
    pass: new FormControl('', [Validators.minLength(4), Validators.required]),
    pass2: new FormControl('', [Validators.minLength(4), Validators.required])
  });

  updateUserForm = new FormGroup({
    username: new FormControl('', [Validators.minLength(4), Validators.required]),
    email: new FormControl('', [Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"), Validators.minLength(7), Validators.required]),
    phoneNumber: new FormControl('', [Validators.pattern("[0-9]+"), Validators.minLength(9), Validators.maxLength(9), Validators.required]),
    role: new FormControl('', Validators.required)
  });

  errorMessagePass = '';
  errorUsername = '';
  errorEmail = '';
  errorNumber = '';
  selectedItem = false;
  selectedRole!: string;
  selectedUserId!: string;
  passwordPlaceholder = "Hasło"

  @ViewChild(MatSort) sort!: MatSort;

  constructor(private api: ApiService, private storage: TokenStorageService, private router: Router) { }

  ngOnInit(): void {
    this.api.getUsers().subscribe(data => {
      this.dataSource = new MatTableDataSource(data)
      console.log(this.dataSource);
      this.dataSource.sort = this.sort;
    },
      err => {
        console.log(err);
      }
    )
  }

  updatePassword(): void {
    if (this.checkPasswords()) {
      const userPassRequest = new UserPasswordRequest();
      userPassRequest.id = this.selectedUserId;
      userPassRequest.newPassword = this.updatePasswordForm.value.pass;
      userPassRequest.oldPassword = this.updatePasswordForm.value.pass;

      this.api.updateUserPasswordByAdmin(userPassRequest).subscribe(() => {
        console.log("hasło zmienione");
        window.location.reload();
      },
        err => {
          console.log(err);
        })
    } else {
      this.errorMessagePass = "Hasła nie są takie same";
      this.updatePasswordForm.get('pass')!.setErrors({ valid: false });
      this.updatePasswordForm.get('pass2')!.setErrors({ valid: false });
  
    }

  }
  checkPasswords(): boolean {
    if (this.updatePasswordForm.value.pass == this.updatePasswordForm.value.pass2)
      return true;
    else
      return false;
  }

  changeRole(role: string): void {

  }

  onDeleteRowClicked(row: UserData): void {
    this.api.deleteUser(row.id).subscribe(() => {
      window.location.reload();
    },
      err => {
        console.log(err);
      })
  }
  onEditRowClicked(row: UserData): void {
    this.selectedItem = true;
    this.selectedUserId = row.id;

    this.updateUserForm.setValue({
      username: row.username,
      email: row.email,
      phoneNumber: row.phoneNumber,
      role: row.role
    });
  }

  updateUser(): void {
    this.updateUserForm.markAllAsTouched();
    const user = new UserData();
    user.email = this.updateUserForm.value.email;
    user.username = this.updateUserForm.value.username;
    user.phoneNumber = this.updateUserForm.value.phoneNumber;
    user.role = this.updateUserForm.value.role;
    user.id = this.selectedUserId;

    this.api.updateUserByAdmin(user).subscribe(() => {
      console.warn("done");
      if (user.username != this.storage.getUserDetails().username && user.id == this.storage.getUserDetails().id) {
        this.router.navigate(["/logout"], { state: { info: 'Zmieniono login, zaloguj się ponownie' } });
      }
      else
        window.location.reload();

    },
      err => {
        console.log(err);
        this.checkError(err.error.message);
      })
  }

  checkError(msg: string): void {
    if (msg == "Username is taken") {
      this.errorUsername = "Login jest zajęty";
      this.updateUserForm.get('username')!.setErrors({ valid: false });
    }
    if (msg == "Email is taken") {
      this.errorEmail = "Email jest zajęty";
      this.updateUserForm.get('email')!.setErrors({ valid: false });
    }
    if (msg == "phone number is taken") {
      this.errorNumber = "Numer telefonu jest zajęty";
      this.updateUserForm.get('phoneNumber')!.setErrors({ valid: false });
    }

  }
}
