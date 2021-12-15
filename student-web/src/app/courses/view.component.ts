import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RestResponse } from '@app/_models/rest.response.pojo';
import { Student } from '@app/_models/student';
import { StudentService } from '@app/_services/student.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.less']
})
export class ViewComponent implements OnInit {
  id!: string;
  students!: Student[];
  restResp: RestResponse ;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private studentService: StudentService
    ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.studentService.getAllStudentByCourseId(this.id).subscribe(res => {
      if (res.MsgCode === '00000') {
        this.restResp = res;
        this.students = this.restResp.Data;
      }

    });

  }

}
