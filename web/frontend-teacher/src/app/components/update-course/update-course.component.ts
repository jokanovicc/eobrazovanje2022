import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from 'src/app/models/course.interface';
import { CourseService } from 'src/app/services/course.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-course',
  templateUrl: './update-course.component.html',
  styleUrls: ['./update-course.component.css']
})
export class UpdateCourseComponent implements OnInit {

  course: Course;
  id: number;
  message = '';
  errorMsg = '';

  constructor(private courseService: CourseService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getSubject();
  }

  getSubject() {
    this.courseService.getCourse(this.id).subscribe((Response) => {
      this.course = Response;

    })
  }


  onSubmit() {
    this.courseService.updateCourse(this.id, this.course)
      .subscribe({
        next: (x) => {
          Swal.fire("Uspesno", "Izmenjen kurs", 'success')
        },
        error: (err: any) => this.handleError(err),
      });


  }


  handleError(err: any) {
    if (err.error && err.error.responseMessage) {
      this.errorMsg = err.error.responseMessage;
    } else {
      this.errorMsg = 'Sva polja moraju biti pravilno popunjena!';
    }
  }





}
