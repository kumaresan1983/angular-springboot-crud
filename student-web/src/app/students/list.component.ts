import { Component, OnInit } from '@angular/core';
import { PagerPojo } from '@app/_models/pager.pojo';
import { RestResponse } from '@app/_models/rest.response.pojo';
import { Student } from '@app/_models/student';
import { StudentService } from '@app/_services/student.service';
import { first } from 'rxjs/operators';


@Component({ templateUrl: 'list.component.html' })
export class ListComponent implements OnInit {
    students!: Student[];
    restResp: RestResponse ;
    pager: PagerPojo = new PagerPojo();
    sortField = '';
    sortOrder = 'asc';
  
    constructor(private studentService: StudentService) {}

    ngOnInit() {
        this.studentService.getAllStudent().subscribe(res => {
            if (res.MsgCode === '00000') {
              this.restResp = res;
              this.students = this.restResp.Data;
            }
      
          });
    }

    deleteUser(id: string) {
        const user = this.students.find(x => x.id === id);
        if (!user) return;
        user.isDeleting = true;
        this.studentService.deleteStudent(id)
            .pipe(first())
            .subscribe(() => this.students = this.students.filter(x => x.id !== id));
    }
}