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
export class StudentService {
    constructor(private http: HttpClient, private httpSrv: HttpService) { }


    getAllStudent(): Observable<RestResponse> {
        return this.httpSrv.get(`${baseUrl}/students`);
    }

    getStudentById(id: string) {
        return this.http.get<User>(`${baseUrl}/student/${id}`);
    }

    saveStudent(obj: any) {
        const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
        let jsonObj = JSON.stringify(obj);
        return this.http.post<RestResponse>(`${baseUrl}/student`, jsonObj, {headers: headers});
    }

    updateStudent(id: string, obj: any) {
        const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
        let jsonObj = JSON.stringify(obj);
        return this.http.put(`${baseUrl}/student/${id}`, jsonObj, {headers: headers});
    }
    
    deleteStudent(id: string) {
        return this.http.delete(`${baseUrl}/student/${id}`);
    }

    getAllStudentByCourseId(id: string): Observable<RestResponse> {
        return this.httpSrv.get(`${baseUrl}/studentsByCourseId/${id}`);
    }

}