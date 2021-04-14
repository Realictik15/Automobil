import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {GearBox} from '../model/gearBox';

const GEAR_API = 'http://localhost:8881/gear';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class GearboxService {

  constructor(private http: HttpClient) {
  }

  getGearBoxes(): Observable<GearBox[]> {
    return this.http.get<GearBox[]>(GEAR_API, httpOptions);
  }

  getGearBox(id: bigint): Observable<GearBox> {
    return this.http.get<GearBox>(GEAR_API + '/' + id, httpOptions);
  }
}