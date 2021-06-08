import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { UserData } from 'src/app/models/UserData';
import { ApiService } from 'src/app/services/api.service';

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
    email:  new FormControl('', [Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"), Validators.minLength(7), Validators.required]),
    phoneNumber: new FormControl('', [Validators.pattern("[0-9]+"), Validators.minLength(9), Validators.maxLength(9), Validators.required]),
    role: new FormControl('', Validators.required)
  });

  errorMessagePass = '';
  errorMessage = '';
  selectedItem = false;
  selectedRole!: string;

  @ViewChild(MatSort) sort!: MatSort;

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.api.getUsers().subscribe(data=>{
      this.dataSource = new MatTableDataSource(data)
      console.log(this.dataSource);
      this.dataSource.sort = this.sort;
    },
    err=> {
      console.log(err);
    }
    )
  }

  updatePassword():void {

  }

  changeRole(role:string):void {

  }
  onDeleteRowClicked(row: UserData):void {

  }
  onEditRowClicked(row: UserData):void {

  }

  updateUser():void {

  }
  
}
