import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Course } from '../models/course.interface';
import { Performance } from '../models/performance.interface';

@Injectable({ providedIn: 'root' })
export class PerformanceService {
  constructor(private http: HttpClient) {}

  getPerformances() {
    return this.http.get<Performance[]>(
      environment.backend_endpoint + `performances`
    );
  }
}
