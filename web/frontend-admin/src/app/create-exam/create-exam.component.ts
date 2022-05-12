import { Component, OnInit } from '@angular/core';
import { ExamPeriod } from '../models/exam-period.interface';
import { Performance } from '../models/performance.interface';
import { CourseService } from '../services/course.service';
import { ExamPeriodService } from '../services/exam-period.service';
import { ExamService } from '../services/exam.service';
import { PerformanceService } from '../services/performance.service';
import { CreateExamRequest } from './create-exam-request.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-create-exam',
  templateUrl: './create-exam.component.html',
  styleUrls: ['./create-exam.component.css'],
})
export class CreateExamComponent implements OnInit {
  public courses: Performance[];
  public periods: ExamPeriod[];

  public selectedCourse: Performance;
  public selectedPeriod: ExamPeriod;
  public classroom: string;
  public date: Date;

  public showSuccessMsg: boolean = false;
  public showErrorMsg: boolean = false;

  constructor(
    private performanceService: PerformanceService,
    private examPeriodService: ExamPeriodService,
    private examService: ExamService
  ) {}

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
