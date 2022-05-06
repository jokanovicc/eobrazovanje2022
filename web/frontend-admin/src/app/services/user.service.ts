import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Teacher } from '../models/models.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }


  getTeachers(){
    return this.http.get<Teacher[]>(environment.backend_endpoint + `users/teachers`);
  }

  createTeacher(teacher: any){
    return this.http.post(environment.backend_endpoint+`users`, teacher).subscribe((result)=> {
      console.warn(result)
    })
    console.warn(teacher);

  
  }



}
