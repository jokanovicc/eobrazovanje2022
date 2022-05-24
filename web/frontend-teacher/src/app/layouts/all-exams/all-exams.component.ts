import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ExamStatus } from 'src/app/models/exam-status.enum';
import { PerformanceExam } from 'src/app/models/performance-exam.interface';
import { ExamService } from 'src/app/services/exam.service';
import ExamStatusHelper from 'src/app/shared/exam-status-helper';

@Component({
  selector: 'app-all-exams',
  templateUrl: './all-exams.component.html',
  styleUrls: ['./all-exams.component.css'],
})
export class AllExamsComponent implements OnInit {
  examPeriods: PerformanceExam[];
  errorMsg: string = '';
  public exam: PerformanceExam;

  constructor(private examService: ExamService, private router: Router) {}

  ngOnInit(): void {
    this.examService.getExams().subscribe((r) => {
      this.examPeriods = r;
    });
  }

  publishResults(tip: string, id: any) {
    this.exam = this.examPeriods.find((x) => (x.id = id));
    this.examService.publishResults(tip, id, this.exam).subscribe({
      next: (x: any) => {
        window.location.reload();
      },
      error: (err: any) => this.handleError(err),
    });
  }

  handleError(err: any) {
    console.log(err);
    if (err instanceof HttpErrorResponse) {
      this.errorMsg = err.error;
    } else {
      this.errorMsg = 'Greška!';
    }
    alert(this.errorMsg);
  }

  getStudents(id: any) {
    this.router.navigate(['/student-exam/' + id]);
  }

  getExamStatusName(examStatus: string) {
    return ExamStatusHelper.getExamStatusName(
      ExamStatus[examStatus as keyof typeof ExamStatus]
    );
  }
}
