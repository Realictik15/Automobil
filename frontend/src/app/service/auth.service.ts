import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Auth} from '../model/auth';

const AUTH_API = 'http://localhost:8881/auth';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(user: Auth): Observable<any> {
    return this.http.post<Auth>(AUTH_API + '/login', user, httpOptions);
  }

  register(user): Observable<any> {
    return this.http.post<any>(AUTH_API+'/reg', user, httpOptions);
  }
}
