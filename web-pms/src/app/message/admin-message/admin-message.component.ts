import { Component, OnInit } from '@angular/core';
import { MessageDetails } from 'src/app/models/MessageDetails';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-admin-message',
  templateUrl: './admin-message.component.html',
  styleUrls: ['./admin-message.component.css']
})
export class AdminMessageComponent implements OnInit {


  displayedColumns: string[] = ['login', 'email', 'subject'];
  dataSource!: Array<MessageDetails>;
  message = '';
  subject ='';
  email ='';
  selected = false;


  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.api.getMessages().subscribe(data => {
      this.dataSource = data;
      console.log(this.dataSource);
    },
      err => {
        console.log(err);
      }
    )
  }

  getRecord(row:MessageDetails):void{
    this.selected = true;
    this.message = row.message;
    this.email = row.user.email;
    this.subject = row.subject;
  }

}
