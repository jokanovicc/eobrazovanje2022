import { Course } from "./course.interface";


export interface CourseResponse {
    courses: Course[]
    pagesCount: number;
  }