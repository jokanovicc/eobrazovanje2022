import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from '../models/course.interface';
import { CourseResponse } from '../models/courseResponse.interface';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  

  public course$: BehaviorSubject<Course> = new BehaviorSubject(null as any);

  constructor(private http: HttpClient) {}



  getCourses(pageNumber?: number) {
    const options = {
      params: {
        page: pageNumber ? pageNumber: 0
      }
    }

    return this.http.get<CourseResponse>(
      environment.backend_endpoint + `courses/all`,
      options
    )
  }

  updateCourse(id:any, userDTO:any){
    return this.http.put(environment.backend_endpoint + `courses/${id}`, userDTO);
  }

  createCourse(data: any) {
    return this.http
      .post(environment.backend_endpoint + `courses`, data)
      .subscribe((result) => {
        console.warn(result);
      });
  }

  getCourse(id:any){
    return this.http.get<Course>(environment.backend_endpoint + `courses/${id}`)
  }
}
