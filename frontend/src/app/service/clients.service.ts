import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const USER_API = 'http://localhost:8881/client/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ClientsService {

  constructor(private http: HttpClient) {
  }

  postCompare(idUser: bigint, idAdvert: bigint): Observable<any> {
    return this.http.get<any>(USER_API + idUser + '/advertcomp/' + idAdvert,
      httpOptions);
  }
}
