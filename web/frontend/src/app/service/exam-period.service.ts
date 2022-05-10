import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { ExamPeriod } from '../models/exam-period.interface';

@Injectable({ providedIn: 'root' })
export class ExamPeriodService {
  constructor(private http: HttpClient) {}

  getActive() {
    return this.http.get<ExamPeriod[]>(
      environment.backend_endpoint + `exam-periods`
    );
  }
}
