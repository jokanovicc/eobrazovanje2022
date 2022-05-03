import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Teacher } from '../models/models.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }


  fetchTeachers(){
    return this.http.get<Teacher[]>('http://localhost:8080/api/users/teachers');
  }



}
