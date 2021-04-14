import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Carbody} from '../model/carbody';

const CARB_API = 'http://localhost:8881/carbody/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class CarbodyService {

  constructor(private http: HttpClient) {
  }

  getAllCarBodies(): Observable<Carbody[]> {
    return this.http.get<Carbody[]>(CARB_API, httpOptions);
  }

  getCarBody(id: bigint): Observable<Carbody> {
    return this.http.get<Carbody>(CARB_API + id, httpOptions);
  }

  getBodyByTitle(title: string): Observable<Carbody> {
    return this.http.get<Carbody>(CARB_API + title + '/title', httpOptions);
  }
}
