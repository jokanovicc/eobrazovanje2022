import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-add-subject',
  templateUrl: './add-subject.component.html',
  styleUrls: ['./add-subject.component.css']
})
export class AddSubjectComponent implements OnInit {

  constructor(private courseService: CourseService, private router:Router) { }

  ngOnInit(): void {
  }

  onSubmit(data:any){
    this.courseService.createCourse(data);
    this.router.navigate(['/dashboard'])

  }

}
