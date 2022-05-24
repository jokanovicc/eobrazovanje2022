import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { gradeStudent } from '../models/gradeStudent.interface';
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

  gradeStudent(examId: number, studentId: number, data: gradeStudent) {
    return this.http.put(
      environment.backend_endpoint + `exams/${examId}/students/${studentId}`,
      data
    );
  }

  publishResults(resultsType: string, examId: any, results: PerformanceExam) {
    console.log(results);
    return this.http.put(
      environment.backend_endpoint +
        `exams/results/${examId}?resultsType=${resultsType}`,
      results
    );
  }
}
