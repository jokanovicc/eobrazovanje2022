import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Exam } from '../models/exam.interface';
import { ExamStatus } from '../models/exam-status.enum';

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
}
