import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { StudentsModule } from './students/students.module';
import { CoursesModule } from './courses/courses.module';

const studentsModule = () => import('./students/students.module').then(x => x.StudentsModule);
const coursesModule = () => import('./courses/courses.module').then(x => x.CoursesModule);

const routes: Routes = [
    { path: '',   redirectTo: '/students', pathMatch: 'full' }, 
    // { path: '', component: HomeComponent },
    { path: 'students', loadChildren: studentsModule },
    { path: 'courses', loadChildren: coursesModule },

    // otherwise redirect to home
    // { path: '**', redirectTo: '' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }