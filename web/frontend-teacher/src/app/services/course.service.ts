import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Course } from '../models/course.interface';

@Injectable({ providedIn: 'root' })
export class CourseService {
  constructor(private http: HttpClient) {}

  getCourses() {
    return this.http.get<Course[]>(environment.backend_endpoint + `courses`);
  }


  updateCourse(id: any, userDTO: any) {
    return this.http.put(environment.backend_endpoint + `courses/${id}`, userDTO);
  }

  getCourse(id: any) {
    return this.http.get<Course>(environment.backend_endpoint + `courses/${id}`)
  }
}
