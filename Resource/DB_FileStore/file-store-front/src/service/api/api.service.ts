import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HTTP_CONSTANTS } from 'src/app-config/app.conestant';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  readonly BASE_URL = environment.BASE_URL;
  constructor(private http: HttpClient) {}

  get<T>(url: string): Observable<T> {
    return this.httpRequest(HTTP_CONSTANTS.GET, url, {}, {}, this.header());
  }
  post<T>(url: string, body: Object) {
    return this.httpRequest(HTTP_CONSTANTS.POST, url, body, {}, this.header());
  }
  put<T>(url: string) {
    this.httpRequest(HTTP_CONSTANTS.PUT, url);
  }
  delete<T>(url: string) {
    this.httpRequest(HTTP_CONSTANTS.DELETE, url);
  }
  private header(): HttpHeaders {
    const header = {
      'content-type': 'application/json',
      // Authorization: `Bearer ${this.authService.getToken()}`,
    };
    return new HttpHeaders(header);
  }
  private httpRequest<T>(
    type: string,
    url: string,
    body?: Object,
    params?: Record<string, string>,
    headers?: HttpHeaders
  ): Observable<T> {
    return this.http.request<T>(type, `${this.BASE_URL}/${url}`, {
      body,
      headers,
      params: new HttpParams({ fromObject: { ...params } }),
    });
  }
}
