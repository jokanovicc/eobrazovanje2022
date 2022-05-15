import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Student } from '../models/student.interface';
import { environment } from 'src/environments/environment';
import { tap } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class StudentService {
  student: Student;
  constructor(private http: HttpClient) {}

  fetchCurrentStudent() {
    return this.http.get<Student>(environment.backend_endpoint + `students`);
  }
}
