import {HttpHeaders} from '@angular/common/http';

export interface IRequestParams {
  url: string;
  headers?: HttpHeaders;
  body?: any;
}
