import { Component, OnInit } from '@angular/core';
import { CreateExamRequest } from './create-exam-request.model';
import Swal from 'sweetalert2';
import { ExamPeriod } from 'src/app/models/exam-period.interface';
import { PerformanceService } from 'src/app/services/performance.service';
import { ExamPeriodService } from 'src/app/services/exam-period.service';
import { ExamService } from 'src/app/services/exam.service';
import { Performance } from 'src/app/models/performance.interface';

@Component({
  selector: 'app-create-exam',
  templateUrl: './create-exam.component.html',
  styleUrls: ['./create-exam.component.css'],
})
export class CreateExamComponent implements OnInit {
  courses: Performance[];
  periods: ExamPeriod[];

  selectedCourse: Performance;
  selectedPeriod: ExamPeriod;
  classroom: string;
  date: Date;

  showSuccessMsg: boolean = false;
  showErrorMsg: boolean = false;

  constructor(
    private performanceService: PerformanceService,
    private examPeriodService: ExamPeriodService,
    private examService: ExamService
  ) { }

  ngOnInit(): void {
    this.fetchCourses();
    this.fetchPeriods();
  }

  fetchCourses() {
    this.performanceService
      .getPerformances()
      .subscribe((response: Performance[]) => {
        this.courses = response;
      });
  }

  fetchPeriods() {
    this.examPeriodService.get().subscribe((response: ExamPeriod[]) => {
      this.periods = response;
    });
  }

  pickCourse(course: Performance) {
    this.selectedCourse = course;
  }

  pickPeriod(period: ExamPeriod) {
    this.selectedPeriod = period;
  }

  createExam() {
    const exam = this.getExamRequest();
    this.examService.createExam(exam).subscribe(
      () => {
        this.showMessage({ success: true });
      },
      () => {
        this.showMessage({ success: false });
      }
    );
  }

  getExamRequest(): CreateExamRequest {
    return {
      performanceId: this.selectedCourse.id,
      examPeriodId: this.selectedPeriod.id,
      classroom: this.classroom,
      date: this.date,
    };
  }

  showMessage(obj: any) {
    if (obj.success) {
      Swal.fire({
        icon: 'success',
        title: 'Uspešno kreiran ispit!',
      });
    } else {
      Swal.fire({
        icon: 'error',
        title: 'Došlo je do greške prilikom kreiranja ispita!',
      });
    }
  }
}
