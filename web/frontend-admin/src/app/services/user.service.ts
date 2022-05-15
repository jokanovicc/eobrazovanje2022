import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Teacher } from '../models/models.interface';
import { TeacherResponse } from '../models/teacherResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }


  getTeachers(pageNumber?: number){
    const options = {
      params: {
        page: pageNumber ? pageNumber : 0
      }
    }

    return this.http.get<TeacherResponse>(
      environment.backend_endpoint + `users/teachers`,
      options
    )
    
  }

  createTeacher(teacher: any){
    return this.http.post(environment.backend_endpoint+`users`, teacher).subscribe((result)=> {
      console.warn(result)
    })

  
  }



}
