import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ExamWithStudentInfoResponse } from 'src/app/models/exam-with-student-info-response.interface';
import { ExamService } from 'src/app/services/exam.service';
import { HttpErrorResponse } from '@angular/common/http';
import { PerformanceExam } from 'src/app/models/performance-exam.interface';

@Component({
  selector: 'app-students-exams',
  templateUrl: './students-exams.component.html',
  styleUrls: ['./students-exams.component.css'],
})
export class StudentsExamsComponent implements OnInit {
  public id: any;
  public students: ExamWithStudentInfoResponse[];
  public toggleButton: boolean = true;
  public isButtonVisible: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private examService: ExamService
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getStudents();
  }

  getStudents() {
    this.examService.getRegisteredToExamStudents(this.id).subscribe((r) => {
      this.students = r;
    });
  }
}
