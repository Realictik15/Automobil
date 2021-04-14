import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Sizes} from '../model/sizes';
import {Generation} from '../model/generation';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Transmission} from '../model/transmission';
import {Mark} from '../model/mark';

const TRANS_API = 'http://localhost:8881/transmission';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class TransmissionService {

  constructor(private http: HttpClient) {
  }

  getTransmission(id: bigint): Observable<Transmission> {
    return this.http.get<Transmission>(TRANS_API + '/' + id, httpOptions);
  }

  getAllTransmission(): Observable<Transmission[]> {
    return this.http.get<Transmission[]>(TRANS_API, httpOptions);
  }
  delete(id: bigint): Observable<any> {
    return this.http.delete<any>(TRANS_API + '/' + id, httpOptions);
  }

  postTransmission(transmission: Transmission): Observable<any> {
    return this.http.post<any>(TRANS_API, transmission, httpOptions);
  }
}
