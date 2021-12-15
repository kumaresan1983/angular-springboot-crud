/*******************************************************
**  ©Copyright 2018 Dagang Net Technologies Sdn Bhd.  **
**  Author : PRAVEEN                                  **
*******************************************************/

import { HttpClient } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { RestResponse } from '@app/_models/rest.response.pojo';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private httpClient: HttpClient, private inj: Injector) { }

  public get<T>(url: string): Observable<RestResponse> {
    return this.httpClient.get<RestResponse>(url);
  }


}
