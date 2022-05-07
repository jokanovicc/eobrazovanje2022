import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-teacher',
  templateUrl: './add-teacher.component.html',
  styleUrls: ['./add-teacher.component.css']
})
export class AddTeacherComponent implements OnInit {

  constructor(private userService: UserService, private cd: ChangeDetectorRef,  private router: Router,
    ) { }

  ngOnInit(): void {
  }

  onSubmit(data:any){
    alert("klik")
    this.userService.createTeacher(data);
    this.router.navigate(['/teachers']);

  }

}
