import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Client} from '../model/client';
import {Advertisment} from '../model/advertisment';
import {Compare} from '../model/compare';

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

  getAllClient(idUser: bigint): Observable<Client[]> {
    return this.http.get<Client[]>(USER_API, httpOptions);
  }

  getClient(idUser: bigint): Observable<Client> {
    return this.http.get<Client>(USER_API + idUser, httpOptions);
  }

  postClient(cl: Client): Observable<void> {
    return this.http.post<void>(USER_API, cl, httpOptions);
  }

  patchClient(cl: Client, id: bigint): Observable<void> {
    return this.http.patch<void>(USER_API + id + '/client', cl, httpOptions);
  }

  getClientAdvert(idUser: bigint): Observable<Advertisment[]> {
    return this.http.get<Advertisment[]>(USER_API + idUser + '/advert', httpOptions);
  }

  getClientCompare(idUser: bigint): Observable<Compare[]> {
    return this.http.get<Compare[]>(USER_API + idUser + '/compare', httpOptions);
  }

  deleteClient(idUser: bigint): Observable<void> {
    return this.http.delete<void>(USER_API + idUser, httpOptions);
  }

  deleteClientComp(idComp: bigint): Observable<void> {
    return this.http.delete<void>(USER_API + 'comparedelete/' + idComp, httpOptions);
  }

  postCompare(idUser: bigint, idAdvert: bigint): Observable<any> {
    return this.http.get<any>(USER_API + idUser + '/advertcomp/' + idAdvert,
      httpOptions);
  }
}
