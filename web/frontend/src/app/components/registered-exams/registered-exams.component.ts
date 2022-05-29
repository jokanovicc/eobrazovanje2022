import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Exam } from 'src/app/models/exam.interface';
import { ExamService } from 'src/app/service/exam.service';

@Component({
  selector: 'app-registered-exams',
  templateUrl: './registered-exams.component.html',
  styleUrls: ['./registered-exams.component.css'],
})
export class RegisteredExamsComponent implements OnInit {
  public exams: Exam[];
  public selectedExamId: number;
  constructor(
    private examService: ExamService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.getRegisteredExams();
  }

  getRegisteredExams() {
    this.examService.getExams('REGISTERED').subscribe((exams: Exam[]) => {
      this.exams = exams;
    });
  }

  deregisterExam(): void {
    this.examService.deregisterExam(this.selectedExamId).subscribe(() => {
      this.getRegisteredExams();
    });
  }

  openDeregisterExamModal(content: any, examId: number): void {
    this.selectedExamId = examId;
    this.modalService.open(content, { centered: true });
  }
}
