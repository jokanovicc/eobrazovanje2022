import { Component, OnInit } from '@angular/core';
import { ExamStatus } from 'src/app/models/exam-status.enum';
import { Exam } from 'src/app/models/exam.interface';
import { ExamService } from 'src/app/service/exam.service';
import ExamStatusHelper from 'src/app/shared/exam-status-helper';

@Component({
  selector: 'app-exam-history',
  templateUrl: './exam-history.component.html',
  styleUrls: ['./exam-history.component.css'],
})
export class ExamHistoryComponent implements OnInit {
  public exams: Exam[];

  constructor(private examService: ExamService) {}

  ngOnInit(): void {
    this.examService.getExams().subscribe((exams: Exam[]) => {
      this.exams = exams;
    });
  }

  getExamStatusName(examStatus: string) {
    return ExamStatusHelper.getExamStatusName(
      ExamStatus[examStatus as keyof typeof ExamStatus]
    );
  }
}
