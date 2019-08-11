import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TransportService } from '../transport.service';
import {map} from 'rxjs/operators';
import { ENDPOINTS } from '../../constants/endpoints';
import {TokenService} from "../token/token.service";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private _isLoggedIn: boolean;
  private _isAdmin: boolean;

  constructor(private transport: TransportService, private token: TokenService) {
  }

  get isLoggedIn(): boolean {
    return this._isLoggedIn;
  }

  get isAdmin(): boolean {
    return this._isAdmin;
  }

  set isAdmin(value: boolean) {
    this._isAdmin = value;
  }

  public sendToken(token: string): Observable<any> {
    const url = `${ENDPOINTS.SERVICE_PREFIX}/login/oauth`;
    const body = JSON.stringify(token);

    return this.transport.post({url,body})
      .pipe(
        map((res: Response | any) => {
          this.token.token = res.token;
          this._isLoggedIn = true;
          return res;
        })
      );
  }

  public checkLogin(login: string): Observable<any> {
    const url = `${ENDPOINTS.SERVICE_PREFIX}/test/backdoor`;
    const body = {login};

    return this.transport.post({url, body})
      .pipe(
        map(res => {
          this.token.token = res.token;
          this._isLoggedIn = true;
          return res;
        })
      );
  }
}
