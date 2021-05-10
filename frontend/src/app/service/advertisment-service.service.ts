import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Advertisment} from '../model/advertisment';
import {PageAdvert} from '../model/PageAdvert';
import {Filter} from '../model/filter';
import {Report} from '../model/Report';


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

  getAllAvalibleAdvertPage(page: number, size: number): Observable<PageAdvert> {
    return this.http.get<PageAdvert>(ADVERT_API + 'all' + '?page=' + page + '&size=' + size, httpOptions);
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

  getReport(id: bigint,vin:string): Observable<Report> {
    return this.http.get<Report>(ADVERT_API + id + '/report/'+vin, httpOptions);
  }

  deleteAdvert(id: bigint): Observable<any> {
    return this.http.delete<any>(ADVERT_API + id, httpOptions);
  }

  userDelete(id: bigint): Observable<any> {
    return this.http.get<any>(ADVERT_API + id + '/userdelete', httpOptions);
  }

  patchAdvert(advert: Advertisment, id: bigint): Observable<any> {
    return this.http.patch<any>(ADVERT_API + id + '/update', advert, httpOptions);
  }


  postAdvert(form:FormData): Observable<any> {
    return this.http.post<any>('http://localhost:8881/advert/', form );
  }

  postAdvertFilters(filters: Filter, page: number, size: number): Observable<any> {
    console.log(filters);
    return this.http.post<any>(ADVERT_API + 'all/filters' + '?page=' + page + '&size=' + size, filters, httpOptions);
  }
}
