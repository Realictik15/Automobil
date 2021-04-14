import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Mark} from '../model/mark';
import {Model} from '../model/model';
import {Client} from '../model/client';

const MARK_API = 'http://localhost:8881/marks';

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
    return this.http.get<Mark[]>(MARK_API, httpOptions);
  }

  getMark(id: bigint): Observable<Mark> {
    return this.http.get<Mark>(MARK_API + '/' + id, httpOptions);
  }

  getModelById(id: bigint): Observable<Model[]> {
    return this.http.get<Model[]>(MARK_API + '/' + id + '/models', httpOptions);
  }

  getMarkByTitle(title: string): Observable<Mark> {
    return this.http.get<Mark>(MARK_API + '/title/' + title, httpOptions);
  }

  postMark(mark: Mark): Observable<void> {
    return this.http.post<void>(MARK_API, mark, httpOptions);
  }

  deleteMark(id: bigint): Observable<void> {
    return this.http.delete<void>(MARK_API + '/' + id, httpOptions);
  }
}

