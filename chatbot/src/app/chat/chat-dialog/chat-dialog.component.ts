import { Component, OnInit } from '@angular/core';
import { ChatService, Message } from '../chat.service';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/scan';


@Component({
  selector: 'chat-dialog',
  templateUrl: './chat-dialog.component.html',
  styleUrls: ['./chat-dialog.component.css']
})
export class ChatDialogComponent implements OnInit {

  messages: Observable<Message[]>;
  formValue: string;

  constructor(private chatService: ChatService) { }

  ngOnInit() {
    // this.chatService.talk();
    this.messages = this.chatService.conversation.asObservable()
      .scan((acc, val) => acc.concat(val));

  }

  sendMessage() {
    this.chatService.converse(this.formValue);
    this.formValue = '';
  }

}
