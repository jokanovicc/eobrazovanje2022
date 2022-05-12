import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Course } from '../models/course.interface';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  constructor(private http: HttpClient) {}

  getCourses() {
    return this.http.get<Course[]>(environment.backend_endpoint + `courses`);
  }

  createCourse(data: any) {
    return this.http
      .post(environment.backend_endpoint + `courses`, data)
      .subscribe((result) => {
        console.warn(result);
      });
  }
}
