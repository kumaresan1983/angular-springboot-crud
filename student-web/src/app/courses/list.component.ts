import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { UserService } from '@app/_services';
import { CourseService } from '@app/_services/course.service';
import { Course } from '@app/_models/course';
import { RestResponse } from '@app/_models/rest.response.pojo';

@Component({ templateUrl: 'list.component.html' })
export class ListComponent implements OnInit {
    courses!: Course[];
    restResp: RestResponse ;

    constructor(private courseService: CourseService) {}

    ngOnInit() {
        this.courseService.getAllCourses().subscribe(res => {
            if (res.MsgCode === '00000') {
              this.restResp = res;
              this.courses = this.restResp.Data;
            }      
          });
    }


}