import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Exam } from '../models/exam.interface';
import { ExamStatus } from '../models/exam-status.enum';
import { PerformanceExam } from '../models/performance-exam.interface';
import { Attending } from '../models/attending.interface';

@Injectable({ providedIn: 'root' })
export class AttendingService {
  constructor(private http: HttpClient) {}

  getLatestForPerformance(performanceId: number) {
    const options = {
      params: {
        performanceId: performanceId,
      },
    };

    return this.http.get<Attending>(
      environment.backend_endpoint +
        `attendings/performances/${performanceId}/latest`,
      options
    );
  }
}
