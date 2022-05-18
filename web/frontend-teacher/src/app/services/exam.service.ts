import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { PerformanceExam } from '../models/performance-exam.interface';

@Injectable({
  providedIn: 'root',
})
export class ExamService {
  constructor(private http: HttpClient) {}

  getExams() {
    return this.http.get<PerformanceExam[]>(
      environment.backend_endpoint + `exams/teacher-exams`
    );
  }

  getRegisteredToExamStudents(id: any) {
    return this.http.get<any[]>(
      environment.backend_endpoint+`exams/${id}/students`
    );
  }
}
