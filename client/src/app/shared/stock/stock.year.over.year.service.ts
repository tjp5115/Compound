import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map'
import { Observable } from 'rxjs';

@Injectable()
export class StockYearOverYearService {

constructor(private http: Http) {}

  getSymbol(symbol : String): Observable<any> {
    return this.http.get('http://localhost:8080/year-over-year/stock/?symbol='+symbol)
      .map((response: Response) => response.json());
  }
}
