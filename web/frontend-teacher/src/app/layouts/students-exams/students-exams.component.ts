import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ExamWithStudentInfoResponse } from 'src/app/models/exam-with-student-info-response.interface';
import { ExamService } from 'src/app/services/exam.service';

@Component({
  selector: 'app-students-exams',
  templateUrl: './students-exams.component.html',
  styleUrls: ['./students-exams.component.css'],
})
export class StudentsExamsComponent implements OnInit {
  public id: any;
  public students: ExamWithStudentInfoResponse[];

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
