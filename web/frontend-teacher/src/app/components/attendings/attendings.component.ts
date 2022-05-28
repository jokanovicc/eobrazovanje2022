import { Component, OnInit } from '@angular/core';
import { PerformanceService } from 'src/app/services/performance.service';
import { Performance } from 'src/app/models/performance.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-attendings',
  templateUrl: './attendings.component.html',
  styleUrls: ['./attendings.component.css']
})
export class AttendingsComponent implements OnInit {

  public courses: Performance[];

  constructor(private performanceService: PerformanceService, private router:Router
    ) { }

  ngOnInit(): void {
    this.fetchCourses();
  }


  fetchCourses() {
    this.performanceService
      .getPerformances()
      .subscribe((response: Performance[]) => {
        this.courses = response;
      });
  }

  getSubject(id:any){
    this.router.navigate(['/course/' + id]);

  }

}
