import { Course } from './course';

export class Student {
    id!: string;
    name!: string;
    age!: string;
    contactNo!: string;
    courses!: Course[];
    isDeleting: boolean = false;
}