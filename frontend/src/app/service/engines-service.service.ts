import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {CompleSet} from '../model/compleSet';
import {Engin} from '../model/engin';

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

  getAllEngines(): Observable<Engin[]> {
    return this.http.get<Engin[]>(ENGIN_API, httpOptions);
  }
  getEngin(id: bigint): Observable<Engin> {
    return this.http.get<Engin>(ENGIN_API + '/' + id, httpOptions);
  }

  postEngin(engin: Engin): Observable<void> {
    return this.http.post<void>(ENGIN_API, engin, httpOptions);
  }
}
