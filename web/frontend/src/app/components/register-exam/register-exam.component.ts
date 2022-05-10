import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Attending } from 'src/app/models/attending.interface';
import { ExamPeriod } from 'src/app/models/exam-period.interface';
import { PerformanceExam } from 'src/app/models/performance-exam.interface';
import { AttendingService } from 'src/app/service/attending.service';
import { ExamPeriodService } from 'src/app/service/exam-period.service';
import { ExamService } from 'src/app/service/exam.service';

@Component({
  selector: 'app-register-exam',
  templateUrl: './register-exam.component.html',
  styleUrls: ['./register-exam.component.css'],
})
export class RegisterExamComponent implements OnInit {
  public exams: PerformanceExam[];
  public selectedExam: PerformanceExam;

  public examPeriods: ExamPeriod[];
  public selectedPeriod: ExamPeriod;

  public showSuccessMessage: boolean;
  public showErrorMessage: boolean;

  constructor(
    private examService: ExamService,
    private attendingService: AttendingService,
    private examPeriodService: ExamPeriodService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.examService
      .getExamsForRegistration()
      .subscribe((exams: PerformanceExam[]) => {
        this.exams = exams;
        console.log(exams);
      });
    this.examPeriodService.getActive().subscribe((periods: ExamPeriod[]) => {
      this.examPeriods = periods;
      console.log(periods);
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
              this.exams = this.exams.filter(
                (exam) => exam.id != this.selectedExam.id
              );
              this.selectedExam = null;
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

  pickPeriod(period: ExamPeriod) {
    console.log(period);
    this.selectedPeriod = period;
    this.examService
      .getExamsInPeriod(period.id)
      .subscribe((exams: PerformanceExam[]) => {
        this.exams = exams;
        console.log(exams);
      });
  }

  examRegistrationPeriodPassed(exam: PerformanceExam): boolean {
    var threeDaysFromNow = new Date(
      new Date().getTime() + 3 * 24 * 60 * 60 * 1000
    );
    console.log(threeDaysFromNow);
    console.log(exam.date);
    console.log(exam.date < threeDaysFromNow);

    return new Date(exam.date) < threeDaysFromNow;
  }
}
