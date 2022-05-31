import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Course } from '../models/course.interface';
import { Performance } from '../models/performance.interface';
import { PerformanceDTO } from '../models/performance-dto.interface';
import { PerformanceTeacher } from '../models/performance-teacher.interface';
import { Teacher } from '../models/teacher.interface';
import { BehaviorSubject, tap } from 'rxjs';
import { Attending } from '../models/attending.interface';
import { AttendingResponse } from '../models/attending-pageable.interface';

@Injectable({ providedIn: 'root' })
export class PerformanceService {
  constructor(private http: HttpClient) {}

  getPerformances() {
    return this.http.get<Performance[]>(
      environment.backend_endpoint + `performances`
    );
  }

  getStudentByPerformance(id: any) {
    return this.http.get<AttendingResponse>(
      environment.backend_endpoint + `attendings/performances/${id}`
    );
  }

  get() {
    return this.http.get<PerformanceDTO[]>(
      environment.backend_endpoint + `performances/all`
    );
  }

  getById(id: any) {
    return this.http.get<PerformanceTeacher>(
      environment.backend_endpoint + `performances/${id}`
    );
  }

  deleteTeacherFromPerformance(perfId: any, teachId: any) {
    this.http
      .delete(
        environment.backend_endpoint +
          `performances/${perfId}/teachers/${teachId}`
      )
      .subscribe(() => 'Delete successful');
  }

  getTeacherForPerformance(perfId: any) {
    return this.http.get<Teacher[]>(
      environment.backend_endpoint + `performances/list-teachers/${perfId}`
    );
  }

  addTeacherToPerformance(perfId: any, data: any) {
    return this.http.post(
      environment.backend_endpoint + `performances/${perfId}/teachers`,
      data
    );
  }

  deleteStudentFromPerformance(perfId: any, studId: any) {
    this.http
      .delete(
        environment.backend_endpoint +
          `attendings/students/${studId}/performance/${perfId}`
      )
      .subscribe(() => 'Delete successful');
  }
}
