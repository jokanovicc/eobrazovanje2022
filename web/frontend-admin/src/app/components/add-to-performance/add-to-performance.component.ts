import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Teacher } from 'src/app/models/teacher.interface';
import { PerformanceService } from 'src/app/services/performance.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-to-performance',
  templateUrl: './add-to-performance.component.html',
  styleUrls: ['./add-to-performance.component.css']
})
export class AddToPerformanceComponent implements OnInit {

  addPerformanceForm: FormGroup;
  message = "";
  errorMsg = "";
  id:any;
  roles: any = ["PROFESOR", "ASISTENT", "PREDAVAC"]
  teachers: Teacher[];

  constructor(private route:ActivatedRoute, private router: Router, private performanceService:PerformanceService,private formBuilder: FormBuilder,
    ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.fetchTeachers();
    this.addPerformanceForm = this.formBuilder.group({
      role: ['', [Validators.required]],
      teacher: ['', [Validators.required]]
    });
  }

  changeTeacher(e:any){
    console.log(e.target.value);
    this.teacher?.setValue(e.target.value, {
      onlySelf: true,
    });


  }

  fetchTeachers(){
    this.performanceService.getTeacherForPerformance(this.id).subscribe((Response) => {
      this.teachers = Response;
      console.log(this.teachers);
    })
  }

  addToPerf(){
    this.performanceService.addTeacherToPerformance(this.id,this.addPerformanceForm.value).subscribe({
      next: (x: any) => {
        Swal.fire('Uspešno!','Nastavnik je dodat na predmeta.','success').then(() => this.router.navigate([`/performances/${this.id}`]))

      },
      error: (err: any) => this.handleError(err)
    });
  }

  get role() {
    return this.addPerformanceForm.get('role');
  }

  get teacher(){
    return this.addPerformanceForm.get("teacher")
  }


  changeRole(e: any) {
    this.role?.setValue(e.target.value, {
      onlySelf: true,
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
