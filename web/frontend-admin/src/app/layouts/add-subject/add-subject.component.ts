import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CourseService } from 'src/app/services/course.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-subject',
  templateUrl: './add-subject.component.html',
  styleUrls: ['./add-subject.component.css']
})
export class AddSubjectComponent implements OnInit {

  public addCourseForm: FormGroup;
  public message = '';
  errorMsg = '';

  constructor(private courseService: CourseService, private router:Router,
    private formBuilder: FormBuilder
    
    
    ) { }

  ngOnInit(): void {
    this.addCourseForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      sylabus: ['', [Validators.required]],
      espb: ['', [Validators.required]],
    });
  }

  setCourse(){
    this.courseService.createCourse(this.addCourseForm.value).subscribe({
      next: (x: any) => {
        this.router.navigate(['/dashboard']);
      },
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
