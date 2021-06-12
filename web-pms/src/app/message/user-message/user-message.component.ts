import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MessageRequest } from 'src/app/models/MessageRequest';
import { ApiService } from 'src/app/services/api.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-user-message',
  templateUrl: './user-message.component.html',
  styleUrls: ['./user-message.component.css']
})
export class UserMessageComponent implements OnInit {

  infoMessage = '';
  messageForm = new FormGroup({
    subject: new FormControl('', [Validators.minLength(4), Validators.required]),
    message: new FormControl('', [Validators.minLength(10), Validators.required])
  });

  constructor(private api: ApiService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
  }

  sendMessage(): void { 
    const msg = new MessageRequest();
    msg.message = this.messageForm.value.message;
    msg.subject = this.messageForm.value.subject;
    msg.userId = this.tokenStorage.getUserDetails().id;
    this.api.sendMessage(msg).subscribe(()=>{
      this.infoMessage = "Wysłano";
    },
    err=> {
      console.log(err);
    })
  }

}
