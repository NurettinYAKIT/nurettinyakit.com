import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';


@Injectable()
export class HttpService {

  private baseURL = 'http://localhost:8090/';

  constructor(public http: Http) {
  }

  /** Rest Api sample**/
  getApi(apiname: string) {
    return new Promise(resolve => {
      this.http.get(this.baseURL + apiname)
        .map((res: Response) => res.json())
        .subscribe(data => {
          resolve(data);
        }, error => {
          console.log(error);
        });
    });
  }

}
