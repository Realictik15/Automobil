import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Mark} from '../model/mark';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Model} from '../model/model';

const ADVERT_API = 'http://localhost:8881/models';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ModelsService {

  constructor(private http: HttpClient) {
  }

  getAllModel(): Observable<Model[]> {
    return this.http.get<Model[]>(ADVERT_API, httpOptions);
  }
}
