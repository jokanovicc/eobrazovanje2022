import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http: HttpClient) {

   }


   createCourse(data: any){
     return this.http.post(environment.backend_endpoint + `courses`, data).subscribe((result) => {
       console.warn(result);
     })
   }
}
