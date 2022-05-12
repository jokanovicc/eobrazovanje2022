import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PerformanceExam } from '../models/performance-exam.interface';

@Injectable({
  providedIn: 'root',
})
export class ExamService {
  constructor(private http: HttpClient) {}

  getExams() {
    return this.http.get<PerformanceExam[]>(
      'http://localhost:8080/api/exams/teacher-exams'
    );
  }

  getRegisteredToExamStudents(id: any) {
    return this.http.get<any[]>(
      `http://localhost:8080/api/exams/${id}/students`
    );
  }
}
