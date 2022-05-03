import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Attending } from 'src/app/models/attending.interface';
import { PerformanceExam } from 'src/app/models/performance-exam.interface';
import { AttendingService } from 'src/app/service/attending.service';
import { ExamService } from 'src/app/service/exam.service';

@Component({
  selector: 'app-register-exam',
  templateUrl: './register-exam.component.html',
  styleUrls: ['./register-exam.component.css'],
})
export class RegisterExamComponent implements OnInit {
  public exams: PerformanceExam[];
  public selectedExam: PerformanceExam;

  public showSuccessMessage: boolean;
  public showErrorMessage: boolean;

  constructor(
    private examService: ExamService,
    private attendingService: AttendingService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.examService
      .getExamsForRegistration()
      .subscribe((exams: PerformanceExam[]) => {
        this.exams = exams;
        console.log(exams);
      });
  }

  registerExam() {
    this.attendingService
      .getLatestForPerformance(this.selectedExam.performance.id)
      .subscribe((response: Attending) => {
        console.log(response);
        this.examService
          .registerExam(this.selectedExam.id, response.performance.id)
          .subscribe(
            (response: any) => {
              this.setShowSuccessMessage();
            },
            (error) => {
              this.setShowErrorMessage();
            }
          );
      });
  }

  open(content: any): void {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
  }

  setShowSuccessMessage(): void {
    this.showSuccessMessage = true;
    setTimeout(() => {
      this.showSuccessMessage = false;
    }, 10000);
  }

  setShowErrorMessage(): void {
    this.showErrorMessage = true;
    setTimeout(() => {
      this.showErrorMessage = false;
    }, 10000);
  }
}
