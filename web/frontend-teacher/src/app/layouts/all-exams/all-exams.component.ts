import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PerformanceExam } from 'src/app/models/model.interface';
import { ExamService } from 'src/app/services/exam.service';

@Component({
  selector: 'app-all-exams',
  templateUrl: './all-exams.component.html',
  styleUrls: ['./all-exams.component.css']
})
export class AllExamsComponent implements OnInit {

  examPeriods: PerformanceExam[];

  constructor(private examService:ExamService, private router:Router) { }

  ngOnInit(): void {
    this.examService.getExams().subscribe((r) => {
      this.examPeriods = r;
    })
    
  }

  getStudents(id:any){
    this.router.navigate(['/student-exam/'+id]);

  }

}
