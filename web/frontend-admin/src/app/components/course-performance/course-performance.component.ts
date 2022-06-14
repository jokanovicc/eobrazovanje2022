import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PerformanceDTO } from 'src/app/models/performance-dto.interface';
import { PerformanceService } from 'src/app/services/performance.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-course-performance',
  templateUrl: './course-performance.component.html',
  styleUrls: ['./course-performance.component.css']
})
export class CoursePerformanceComponent implements OnInit {

  public performances: any;
  searchText = "";
  constructor(private performanceService: PerformanceService,
    private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.fetchPerformances();
  }



  fetchPerformances() {
    this.performanceService.get().subscribe((response) => {
      this.performances = response;
    })


  }

  sendTeacher(id: any) {
    this.router.navigate(['/performances/' + id])

  }

  Search() {
    if (this.searchText) {
      let searchValue = this.searchText.toLocaleLowerCase();

      this.performances = this.performances.filter((contact: any) => {
        return contact.courseName.toLocaleLowerCase().match(searchValue);

      });

    }
    else {
      this.performanceService.get().subscribe(data => {

        this.performances = data;

      });

    }
  }
}
