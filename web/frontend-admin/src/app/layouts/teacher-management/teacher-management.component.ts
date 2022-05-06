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

  public teachers: Teacher[];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.fetchTeachers();

  }


  fetchTeachers(){
    this.userService.getTeachers().subscribe((response) => {
      this.teachers = response;
    })

  }

}
