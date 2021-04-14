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
import {CompleSet} from '../model/compleSet';
import {Engin} from '../model/engin';
import {Transmission} from '../model/transmission';

const MODIF_API = 'http://localhost:8881/modification';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ModificationService {

  constructor(private http: HttpClient) {
  }

  getAllModif(): Observable<Modification[]> {
    return this.http.get<Modification[]>(MODIF_API, httpOptions);
  }

  getListComplByModif(id: bigint): Observable<CompleSet[]> {
    return this.http.get<CompleSet[]>(MODIF_API+ '/' + id + '/compl', httpOptions);
  }

  getModif(id: bigint): Observable<Modification> {
    return this.http.get<Modification>(MODIF_API + '/' + id, httpOptions);
  }

  getEnginByModif(id: bigint): Observable<Engin> {
    return this.http.get<Engin>(MODIF_API + '/' + id + '/engin', httpOptions);
  }

  getTransmByModif(id: bigint): Observable<Transmission> {
    return this.http.get<Transmission>(MODIF_API + '/' + id + '/transmission', httpOptions);
  }

}
