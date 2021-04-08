import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Advertisment} from '../model/advertisment';

const ADVERT_API = 'http://localhost:8881/advert/';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AdvertismentServiceService {

  constructor(private http: HttpClient) {
  }

  getAllAvalibleAdvert(): Observable<Advertisment[]> {
    return this.http.get<Advertisment[]>(ADVERT_API, httpOptions);
  }

  getAllAvalibleAdvertByClass(id: number): Observable<Advertisment[]> {
    return this.http.get<Advertisment[]>(ADVERT_API + 'class/' + id, httpOptions);
  }

  getAllAdvert(): Observable<Advertisment[]> {
    return this.http.get<Advertisment[]>(ADVERT_API + 'admin/', httpOptions);
  }

  getAdvertById(id: number): Observable<Advertisment> {
    return this.http.get<Advertisment>(ADVERT_API + id, httpOptions);
  }

  getReport(id: number): Observable<Advertisment[]> {
    return this.http.get<Advertisment[]>(ADVERT_API + id + '/report', httpOptions);
  }

  deleteAdvert(id: number): Observable<any> {
    return this.http.delete<any>(ADVERT_API + id, httpOptions);
  }

  userDelete(id: number): Observable<any> {
    return this.http.get<any>(ADVERT_API + id + '/userdelete', httpOptions);
  }

  patchAdvert(advert: Advertisment, id: number): Observable<any> {
    return this.http.patch<any>(ADVERT_API + id + '/update', advert, httpOptions);
  }

  // TODO: change
  postAdvert(advert: Advertisment): Observable<any> {
    return this.http.post<any>(ADVERT_API, advert); // , httpOptions);
  }
}
