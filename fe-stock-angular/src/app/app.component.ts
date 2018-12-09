import { Component, OnInit } from '@angular/core';
import { HttpService } from './services/http.service';
import { Stock } from './model/stock.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [HttpService]
})
export class AppComponent implements OnInit {
  title = 'Finance App';
  stocks: [Stock];

  constructor(private httpService: HttpService) {
  }

  ngOnInit() {
    this.getstocks();
  }

  private getstocks() {
    this.httpService.getApi('stock').then((data: any) => {
      console.log('Rest api result ');
      console.log(data);
      this.stocks = data;
    });
  }


}
