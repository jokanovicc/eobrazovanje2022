import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Student } from 'src/app/models/student.interface';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-student-info',
  templateUrl: './student-info.component.html',
  styleUrls: ['./student-info.component.css'],
})
export class StudentInfoComponent implements OnInit {
  id: number;
  student: Student;
  
  constructor(
    private userService: UserService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getStudent();
  }

  getStudent() {
    this.userService.getStudent(this.id).subscribe({
      next: (x: any) => {
        this.student = x;
      },
    });
  }
}
