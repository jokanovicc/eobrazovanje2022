import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-teacher',
  templateUrl: './add-teacher.component.html',
  styleUrls: ['./add-teacher.component.css']
})
export class AddTeacherComponent implements OnInit {

  addTeacherForm: FormGroup;
  message = "";
  errorMsg = "";
  genders: any = ['muški', 'ženski'];


  constructor(private userService: UserService, private cd: ChangeDetectorRef,  private router: Router,
    private formBuilder:FormBuilder
    ) { }

  ngOnInit(): void {
    this.addTeacherForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      lastname: ['', [Validators.required]],
      jmbg: ['', [Validators.required]],
      address: ['', [Validators.required]],
      gender: ['', [Validators.required]],
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
      email: ['', [Validators.required]],

    
    })
  }

  get gender() {
    return this.addTeacherForm.get('gender');
  }

  changeGender(e: any) {
    this.gender?.setValue(e.target.value, {
      onlySelf: true,
    });
  }

  addTeacher(){
    this.userService.createTeacher(this.addTeacherForm.value).subscribe({
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
