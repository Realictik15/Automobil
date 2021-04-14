import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Mark} from '../model/mark';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Model} from '../model/model';
import {Generation} from '../model/generation';

const MODEL_API = 'http://localhost:8881/models';

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
    return this.http.get<Model[]>(MODEL_API, httpOptions);
  }

  getModel(id: bigint): Observable<Model> {
    return this.http.get<Model>(MODEL_API + '/' + id, httpOptions);
  }

  getGenByIdModel(id: bigint): Observable<Generation[]> {
    return this.http.get<Generation[]>(MODEL_API + '/' + id + '/generetions', httpOptions);
  }

  getModelByTitle(title: string): Observable<Model> {
    return this.http.get<Model>(MODEL_API + '/title/' + title, httpOptions);
  }

  postModel(model: Model): Observable<void> {
    return this.http.post<void>(MODEL_API, model, httpOptions);
  }

  deleteModel(id: bigint): Observable<void> {
    return this.http.delete<void>(MODEL_API + '/' + id, httpOptions);
  }
}
