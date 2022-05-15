import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/course.interface';
import { CourseResponse } from 'src/app/models/courseResponse.interface';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.css']
})
export class SubjectsComponent implements OnInit {

  public courses: Course[]
  public page:number = 0;
  public totalPagesCount:number;



  constructor(private courseService: CourseService, private cd: ChangeDetectorRef, private router:Router) {

   }

  ngOnInit(): void {
    this.fetchTeachers();
  }

  fetchTeachers(){
    this.courseService
      .getCourses(this.page)
      .subscribe((response: CourseResponse) => {

        this.courses = response.courses
        this.totalPagesCount = response.pagesCount;
        this.cd.detectChanges();
      });

  }

  nextPage(){
    this.page = this.page + 1;
    this.fetchTeachers();
  }

  getCourse(id:any){
    this.router.navigate(['/course/' + id])
  }



  previousPage(){
    this.page = this.page - 1;
    this.fetchTeachers();
  }

  getSubject(id:any){
    this.router.navigate(['/course/' + id]);

  }

  createCourse(){
    this.router.navigate(["/add-course"])
  }
}
