import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Mark} from '../model/mark';
import {Model} from '../model/model';

const ADVERT_API = 'http://localhost:8881/marks';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class MarkServiceService {

  constructor(private http: HttpClient) {
  }

  getAllMarks(): Observable<Mark[]> {
    return this.http.get<Mark[]>(ADVERT_API, httpOptions);
  }

  getModelById(id: bigint): Observable<Model[]> {
    return this.http.get<Model[]>(ADVERT_API + '/' + id + '/models', httpOptions);
  }
}

