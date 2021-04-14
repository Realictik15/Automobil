import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Generation} from '../model/generation';
import {Sizes} from '../model/sizes';
import {Modification} from '../model/modification';
import {Carbody} from '../model/carbody';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const SIZE_API = 'http://localhost:8881/sizess';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class SizesService {

  constructor(private http: HttpClient) {
  }

  getSize(id: bigint): Observable<Sizes> {
    return this.http.get<Sizes>(SIZE_API + '/' + id, httpOptions);
  }

  getSizeByidCarbodyAndGen(idC: bigint, idG: bigint): Observable<Generation> {
    return this.http.get<Generation>(SIZE_API + '/generations/' + idC + '/carbody/' + idG, httpOptions);
  }

  deleteGet(id: bigint): Observable<any> {
    return this.http.delete<any>(SIZE_API + '/' + id, httpOptions);
  }

  postAdvert(size: Sizes): Observable<any> {
    return this.http.post<any>(SIZE_API, size, httpOptions);
  }
}
