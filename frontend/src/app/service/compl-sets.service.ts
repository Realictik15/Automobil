import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Client} from '../model/client';
import {CompleSet} from '../model/compleSet';

const COMPL_API = 'http://localhost:8881/compls';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ComplSetsService {

  constructor(private http: HttpClient) {
  }

  getCompl(id: bigint): Observable<CompleSet> {
    return this.http.get<CompleSet>(COMPL_API + '/' + id, httpOptions);
  }

  postCompl(compl: CompleSet): Observable<void> {
    return this.http.post<void>(COMPL_API, compl, httpOptions);
  }

  deleteCompl(id: bigint): Observable<void> {
    return this.http.delete<void>(COMPL_API + '/' + id, httpOptions);
  }
}
