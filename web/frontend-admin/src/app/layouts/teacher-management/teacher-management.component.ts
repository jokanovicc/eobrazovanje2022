import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/services/user.service';
import { Teacher } from '../../models/models.interface';

@Component({
  selector: 'app-teacher-management',
  templateUrl: './teacher-management.component.html',
  styleUrls: ['./teacher-management.component.css']
})
export class TeacherManagementComponent implements OnInit {

  teachers: Observable<Teacher[]>;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.teachers = this.userService.fetchTeachers();
    console.log(this.teachers);
  }

}
