import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from 'src/app/models/course.interface';
import { CourseService } from 'src/app/services/course.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-course-update',
  templateUrl: './course-update.component.html',
  styleUrls: ['./course-update.component.css']
})
export class CourseUpdateComponent implements OnInit {

  public course:Course;
  public id: any;

  constructor(private courseService: CourseService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.getSubject();
  }

  getSubject(){
    this.courseService.getCourse(this.id).subscribe((Response) => {
      console.log(Response);
      this.course = Response;

    })
  }

  onSubmit(){
    this.courseService.updateCourse(this.id, this.course)
    .subscribe(data => {
      console.log(data);
    })

    Swal.fire(
      'Uspešno!',
      'Predmet je ažuriran!',
      'success'
    )

  }





}
