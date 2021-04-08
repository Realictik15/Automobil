import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

const ENGIN_API = 'http://localhost:8881/engines';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class EnginesServiceService {

  constructor(private http: HttpClient) {
  }

  getAllEngines(): Observable<any> {
    return this.http.get(ENGIN_API + '?_limit=2');
  }
}
