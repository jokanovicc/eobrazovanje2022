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
  public message = '';
  errorMsg = '';

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
    .subscribe({
      next: (x: any) => {
        Swal.fire(
          'Uspešno!',
          'Predmet je ažuriran!',
          'success'
        )      },
      error: (err: any) => this.handleError(err),
    });


  }


  handleError(err: any) {
    console.log(err);
    if (err.error && err.error.responseMessage) {
      this.errorMsg = err.error.responseMessage;
    } else {
      this.errorMsg = 'Sva polja moraju biti pravilno popunjena!';
    }
  }





}