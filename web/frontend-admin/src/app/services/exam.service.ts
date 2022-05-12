import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Exam } from '../models/exam.interface';
import { ExamStatus } from '../models/exam-status.enum';
import { PerformanceExam } from '../models/performance-exam.interface';
import { CreateExamRequest } from '../create-exam/create-exam-request.model';

@Injectable({ providedIn: 'root' })
export class ExamService {
  constructor(private http: HttpClient) {}

  createExam(exam: CreateExamRequest) {
    return this.http.post(environment.backend_endpoint + `exams`, exam);
  }
}
