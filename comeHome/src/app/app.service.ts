import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  url = 'http://140.238.179.101:8080';
  private mockedUrl = '/api/results';
  protected mockedHead = new HttpHeaders({
    'Content-Type': 'application/json;odata.metadata=minimal',
    Accept: 'application/json',
  });

  constructor(private httpClient: HttpClient) { }

  protected head = new HttpHeaders({
    Accept: 'application/json',
  });

  getOrgs(): Observable<any> {
    return this.httpClient.get<any>(`${this.url}/eventos`, {headers: this.head})
    .pipe(catchError((res) => this.handleError(res)));
  }
  getApprovedEvents(): Observable<any> {
    return this.httpClient.get<any>(`${this.url}/eventos/aprovados`, {headers: this.head})
    .pipe(catchError((res) => this.handleError(res)));
  }
  protected handleError(error: any): Observable<any> {
    return throwError(error);
  }
  getAllResults(): Observable<any[]> {
    return this.httpClient.get<any[]>(this.mockedUrl);
  }
  closeMirage(): Observable<any> {
    return this.httpClient.get<any>(`${this.mockedUrl}/close`);
  }
  insertResult(result: any): Observable<any> {
    return this.httpClient
      .post<any>(`${this.url}/eventos/create`, result, { headers: this.head })
      .pipe(catchError(async (res) => this.handleError(res)));
  }
  updateOrg(result: any): Observable<any> {
    return this.httpClient
      .put<any>(`${this.url}/usuarios/org/${result.email}/${result.cargoUsuario}`, { headers: this.head })
      .pipe(catchError(async (res) => this.handleError(res)));
  }
  insertUser(result: any): Observable<any> {
    return this.httpClient
      .post<any>(`${this.url}/usuarios/create`, result, { headers: this.head })
      .pipe(catchError(async (res) => this.handleError(res)));
  }
  approveEvent(result: any): Observable<any> {
    return this.httpClient
      .put<any>(`${this.url}/eventos/aprovar/${result}`, { headers: this.head })
      .pipe(catchError(async (res) => this.handleError(res)));
  }
  deleteEvent(id: any, comentario: any): Observable<any> {
    return this.httpClient
      .delete<any>(`${this.url}/eventos/delete/${id}?comentario=${comentario}`, { headers: this.head })
      .pipe(catchError(async (res) => this.handleError(res)));
  }
  participarEvento(id: any, email: any): Observable<any> {
    return this.httpClient.put<any>(`${this.url}/eventos/participar/${id}/${email}`, {headers: this.head})
    .pipe(catchError((res) => this.handleError(res)));
  }
  getConflitoEventos(): Observable<any> {
    return this.httpClient.get<any>(`${this.url}/eventos/conflito-dia`, {headers: this.head})
    .pipe(catchError((res) => this.handleError(res)));
  }
  getDownloadEventoAberto(): Observable<any> {
    return this.httpClient
      .get(`${this.url}/eventos/exportaberto`, { responseType: 'blob' })
      .pipe(catchError(async (res) => this.handleError(res)));
  }
  getDownloadEventoFechado(): Observable<any> {
    return this.httpClient
      .get(`${this.url}/eventos/exportfechado`, { responseType: 'blob' })
      .pipe(catchError(async (res) => this.handleError(res)));
  }
  getDownloadConvidadosAberto(): Observable<any> {
    return this.httpClient
      .get(`${this.url}/eventos/exportconvidadoaberto`, { responseType: 'blob' })
      .pipe(catchError(async (res) => this.handleError(res)));
  }
  getDownloadConvidadosFechado(): Observable<any> {
    return this.httpClient
      .get(`${this.url}/eventos/exportconvidadofechado`, { responseType: 'blob' })
  }
}
