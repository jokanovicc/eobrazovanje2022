import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { ExamStatus } from 'src/app/models/exam-status.enum';
import { ExamWithStudentInfoResponse } from 'src/app/models/exam-with-student-info-response.interface';
import { gradeStudent } from 'src/app/models/gradeStudent.interface';
import { ExamService } from 'src/app/services/exam.service';
import ExamStatusHelper from 'src/app/shared/exam-status-helper';

@Component({
  selector: '[app-student-exam]',
  templateUrl: './student-exam.component.html',
  styleUrls: ['./student-exam.component.css'],
})
export class StudentExamComponent implements OnInit {
  toggleButton: boolean = true;
  isButtonVisible: boolean = false;
  errorMsg = '';
  data: gradeStudent = {
    preExamDutyPoints: 0,
    finalExamPoints: 0,
  };

  @Input() student: ExamWithStudentInfoResponse;
  constructor(private examService: ExamService) {}

  ngOnInit(): void {}

  handleInputStatus() {
    if (this.toggleButton) {
      this.toggleButton = false;
      this.isButtonVisible = true;
    } else {
      this.toggleButton = true;
      this.isButtonVisible = false;
    }
  }

  studentAssessment() {
    this.data.preExamDutyPoints = this.student.preExamDutyPoints;
    this.data.finalExamPoints = this.student.finalExamPoints;
    this.examService
      .gradeStudent(this.student.examId, this.student.studentId, this.data)
      .subscribe({
        error: (err: HttpErrorResponse) => this.handleError(err),
      });
    this.toggleButton = true;
    this.isButtonVisible = false;
  }

  getExamStatusName(examStatus: string) {
    return ExamStatusHelper.getExamStatusName(
      ExamStatus[examStatus as keyof typeof ExamStatus]
    );
  }

  handleError(err: HttpErrorResponse) {
    console.log(err);
    if (err.error && err.error.responseMessage) {
      this.errorMsg = err.error.responseMessage;
    } else {
      this.errorMsg = 'Greška!';
    }
  }
}
