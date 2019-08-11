import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

import { IRequestParams } from '../interfaces/request-params.interface';
import { environment } from '../../../environments/environment';
import {TokenService} from "./token/token.service";

@Injectable({
  providedIn: 'root'
})

export class TransportService {

  constructor(private http: HttpClient, private token: TokenService) {
  }

  public authToken: string = localStorage.getItem('token') || '';

  public get(params: IRequestParams): Observable<any> {
    this.authToken = localStorage.getItem('token');
    let {url, headers} = params;
    headers = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
      'x-token': this.token.token
    });

    return this.http.get(environment.baseUrl + url, {
      headers
    });
  }

  public post(params: IRequestParams): Observable<any> {
    let {url, body, headers} = params;
    headers = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});

    return this.http.post(environment.baseUrl + url, body, {
      headers
    });
  }

  public put(params: IRequestParams): Observable<any> {
    this.authToken = localStorage.getItem('token');
    let {url, body, headers} = params;
    headers = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
      'x-token': this.token.token
    });

    return this.http.put(environment.baseUrl + url, body, {
      headers
    });
  }

  public patch(params: IRequestParams): Observable<any> {
    const {url, body, headers} = params;

    return this.http.patch(environment.baseUrl + url, body, {
      headers
    });
  }

  public delete(params: IRequestParams): Observable<any> {
    const {url, headers} = params;

    return this.http.delete(environment.baseUrl + url, {
      headers
    });
  }
}
