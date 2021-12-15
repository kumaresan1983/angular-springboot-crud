import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControlOptions, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { MultiSelectComponent } from '@syncfusion/ej2-angular-dropdowns';

import { MustMatch } from '@app/_helpers';
import { StudentService } from '@app/_services/student.service';
import { AlertService } from '@app/_services/alert.service';
import { Student } from '@app/_models/student';
import { Course } from '@app/_models/course';
import { CourseService } from '@app/_services/course.service';
import { RestResponse } from '@app/_models/rest.response.pojo';

@Component({ templateUrl: 'add-edit.component.html' })
export class AddEditComponent implements OnInit {
    form!: FormGroup;
    id!: string;
    isAddMode!: boolean;
    loading = false;
    submitted = false;
    student = new Student();
    restResp: RestResponse ;

    @ViewChild('coursesselectionlist', { static: true })
    public coursesList: MultiSelectComponent;

    public courseList: Course[] = [];

    // maps the appropriate column to fields property
    public fields: Object = { text: 'name', value: 'id' };
    // set placeholder to MultiSelect input element
    public placeholder: string = 'Select course';


    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private studentService: StudentService,
        private alertService: AlertService,
        private courseService: CourseService
    ) { }

    ngOnInit() {
        this.id = this.route.snapshot.params['id'];
        this.isAddMode = !this.id;

        const formOptions: AbstractControlOptions = {};
        this.form = this.formBuilder.group({
            name: ['', Validators.required],
            age: ['', Validators.required],
            contactNo: ['', Validators.required]
        }, formOptions);

        if (!this.isAddMode) {
            this.studentService.getStudentById(this.id)
                .pipe(first())
                .subscribe(x => this.form.patchValue(x));
        }

        this.courseService.getAllCourses().subscribe(res => {
            if (res.MsgCode === '00000') {
              this.restResp = res;
              this.courseList = this.restResp.Data;
            }
          });

    }

    // convenience getter for easy access to form fields
    get f() { return this.form.controls; }

    onSubmit() {
        this.submitted = true;

        // reset alerts on submit
        this.alertService.clear();

        // stop here if form is invalid
        if (this.form.invalid) {
            return;
        }

        this.loading = true;
        if (this.isAddMode) {
            this.createStudent();
        } else {
            this.updateStudent();
        }
    }

    private createStudent() {

        this.student.name = this.form.get('name').value;
        this.student.age = this.form.get('age').value;
        this.student.contactNo = this.form.get('contactNo').value;

        let courses: Course[] = [];

        for (let p of this.coursesList.value) {
            let newCourse = new Course();
            newCourse.id = p.toString();
            courses.push(newCourse);
        }
        this.student.courses = courses;


        this.studentService.saveStudent(this.student)
            .pipe(first())
            .subscribe(() => {
                this.alertService.success('User added', { keepAfterRouteChange: true });
                this.router.navigate(['../'], { relativeTo: this.route });
            })
            .add(() => this.loading = false);
    }

    private updateStudent() {

        this.student.name = this.form.get('name').value;
        this.student.age = this.form.get('age').value;
        this.student.contactNo = this.form.get('contactNo').value;

        this.studentService.updateStudent(this.id, this.student)
            .pipe(first())
            .subscribe(() => {
                this.alertService.success('User updated', { keepAfterRouteChange: true });
                this.router.navigate(['../../'], { relativeTo: this.route });
            })
            .add(() => this.loading = false);
    }
}