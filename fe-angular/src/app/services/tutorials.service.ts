import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TutorialsService {

  constructor(private httpClient: HttpClient) { }

  getTutorials() : Observable<any> {
      return this.httpClient.get('http://localhost:4200/api/tutorials/list');
  }

  sendPos(body:any) : Observable<any> {
    return this.httpClient.post('http://localhost:4200/api/tris/pos',body);
  }
  start(giocatore1:string,giocatore2:string) : Observable<any> {
    return this.httpClient.get('http://localhost:4200/api/tris/start?p1='+giocatore1+"&p2="+giocatore2);
  }
}
