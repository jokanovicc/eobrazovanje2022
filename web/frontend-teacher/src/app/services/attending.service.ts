import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Exam } from '../models/exam.interface';
import { ExamStatus } from '../models/exam-status.enum';
import { PerformanceExam } from '../models/performance-exam.interface';
import { Attending } from '../models/attending.interface';
import { CreateAttendingReq } from '../models/create-attending-req.interface';
import { AttendingResponse } from '../models/attending-pageable.interface';

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

  getStudentByPerformance(id: any, pageNumber?: number) {
    const options = {
      params: {
        page: pageNumber ? pageNumber : 0,
      },
    };
    return this.http.get<AttendingResponse>(
      environment.backend_endpoint + `attendings/performances/${id}`,
      options
    );
  }

  createAttending(data: CreateAttendingReq, performanceId: any) {
    return this.http.post(
      environment.backend_endpoint + `attendings/performance/${performanceId}`,
      data
    );
  }

  deleteStudentFromPerformance(perfId: any, studId: any) {
    this.http
      .delete(
        environment.backend_endpoint +
          `attendings/students/${studId}/performance/${perfId}`
      )
      .subscribe(() => 'Delete successful');
  }
}
