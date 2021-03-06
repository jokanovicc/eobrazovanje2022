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
      environment.backend_endpoint + `exams/teacher-exams`
    );
  }

  getRegisteredToExamStudents(id: number) {
    return this.http.get<any[]>(
      environment.backend_endpoint+`exams/${id}/students`
    );
  }

  gradeStudent(examId: number, studentId: number, data: gradeStudent) {
    return this.http.put(
      environment.backend_endpoint + `exams/${examId}/students/${studentId}`,
      data
    );
  }

  publishResults(resultsType: string, examId: number, results: PerformanceExam) {
    console.log(results);
    return this.http.put(
      environment.backend_endpoint +
        `exams/results/${examId}?resultsType=${resultsType}`,
      results
    );
  }
}
