import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Exam } from '../models/exam.interface';
import { ExamStatus } from '../models/exam-status.enum';
import { PerformanceExam } from '../models/performance-exam.interface';

@Injectable({ providedIn: 'root' })
export class ExamService {
  constructor(private http: HttpClient) {}

  getExams(examStatus?: string) {
    const options = {
      params: {
        status: examStatus ? examStatus : 'ALL',
      },
    };

    return this.http.get<Exam[]>(
      environment.backend_endpoint + `exams`,
      options
    );
  }

  getExamsInPeriod(periodId: number) {
    const options = {
      params: {
        examPeriodId: periodId,
      },
    };

    return this.http.get<PerformanceExam[]>(
      environment.backend_endpoint + `exams/registration`,
      options
    );
  }

  getExamsForRegistration() {
    return this.http.get<PerformanceExam[]>(
      environment.backend_endpoint + `exams/registration`
    );
  }

  registerExam(performanceExamId: number, attendingId: number) {
    const options = {};
    return this.http.post(
      environment.backend_endpoint +
        `exams/${performanceExamId}/attending/${attendingId}`,
      options
    );
  }
}
