import { Component, OnInit } from '@angular/core';
import { Exam } from 'src/app/models/exam.interface';
import { ExamService } from 'src/app/service/exam.service';

@Component({
  selector: 'app-registered-exams',
  templateUrl: './registered-exams.component.html',
  styleUrls: ['./registered-exams.component.css'],
})
export class RegisteredExamsComponent implements OnInit {
  public exams: Exam[];
  constructor(private examService: ExamService) {}

  ngOnInit(): void {
    this.examService.getExams('REGISTERED').subscribe((exams: Exam[]) => {
      this.exams = exams;
    });
  }
}
