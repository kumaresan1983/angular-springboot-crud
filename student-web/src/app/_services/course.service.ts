import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { environment } from '@environments/environment';
import { User } from '@app/_models';
import { HttpService } from './http.service';
import { PagerPojo } from '@app/_models/pager.pojo';
import { RestResponse } from '@app/_models/rest.response.pojo';
import { Observable } from 'rxjs';

const baseUrl = `${environment.apiUrl}`;

@Injectable({ providedIn: 'root' })
export class CourseService {
    constructor(private http: HttpClient, private httpSrv: HttpService) { }

    getAllCourses(): Observable<RestResponse> {
        return this.httpSrv.get(`${baseUrl}/courses`);
    }

    saveCourses(obj: any) {
        const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
        let jsonObj = JSON.stringify(obj);
        return this.http.post<RestResponse>(`${baseUrl}/course`, jsonObj, {headers: headers});
    }
    

}