import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Advertisment} from '../model/advertisment';
import {PageAdvert} from '../model/PageAdvert';
import {Filter} from '../model/filter';
import {Generation} from '../model/generation';
import {Sizes} from '../model/sizes';
import {Modification} from '../model/modification';
import {Carbody} from '../model/carbody';

const GEN_API = 'http://localhost:8881/generation';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class GenerationsService {

  constructor(private http: HttpClient) {
  }

  getAllGen(): Observable<Generation[]> {
    return this.http.get<Generation[]>(GEN_API, httpOptions);
  }

  getGen(id: bigint): Observable<Generation> {
    return this.http.get<Generation>(GEN_API + '/' + id, httpOptions);
  }

  getSizesByGen(id: bigint): Observable<Sizes[]> {
    return this.http.get<Sizes[]>(GEN_API + '/' + id + '/size', httpOptions);
  }

  getModifByGen(id: bigint): Observable<Modification[]> {
    return this.http.get<Modification[]>(GEN_API + '/' + id + '/modification', httpOptions);
  }

  getCarBody(id: bigint): Observable<Carbody[]> {
    return this.http.get<Carbody[]>(GEN_API + '/' + id + '/carbody', httpOptions);
  }

  deleteGet(id: bigint): Observable<any> {
    return this.http.delete<any>(GEN_API + '/' + id, httpOptions);
  }

  postGen(gen: Generation): Observable<any> {
    return this.http.post<any>(GEN_API, gen, httpOptions);
  }
}
