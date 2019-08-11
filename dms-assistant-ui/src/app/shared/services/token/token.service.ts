import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class TokenService {
  private _token: string;

  constructor() {
    this._token = localStorage.getItem('token') || null;
  }

  get token() {
    return this._token;
  }

  set token(token: string) {
    localStorage.setItem('token', token);
    this._token = token;
  }

  removeToken() {
    localStorage.removeItem('token');
    this._token = null;
  }

}
