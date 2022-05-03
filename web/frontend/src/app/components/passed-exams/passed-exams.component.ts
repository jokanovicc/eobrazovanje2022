import { Component, OnInit } from '@angular/core';
import { ExamStatus } from 'src/app/models/exam-status.enum';
import { Exam } from 'src/app/models/exam.interface';
import { ExamService } from 'src/app/service/exam.service';

@Component({
  selector: 'app-passed-exams',
  templateUrl: './passed-exams.component.html',
  styleUrls: ['./passed-exams.component.css'],
})
export class PassedExamsComponent implements OnInit {
  public exams: Exam[];
  constructor(private examService: ExamService) {}

  ngOnInit(): void {
    this.examService.getExams('PASSED').subscribe((exams: Exam[]) => {
      this.exams = exams;
    });
  }
}
