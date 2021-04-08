import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

const ADVERT_API = 'http://localhost:8881/marks';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class MarkServiceService {

  constructor(private http: HttpClient) {
  }

  getAllAdvert(): Observable<any> {
    return this.http.get(ADVERT_API);
  }
}
