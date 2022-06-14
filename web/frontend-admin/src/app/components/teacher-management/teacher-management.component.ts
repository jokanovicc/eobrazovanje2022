import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TeacherResponse } from 'src/app/models/teacherResponse.interface';
import { UserService } from 'src/app/services/user.service';
import { Teacher } from '../../models/models.interface';

@Component({
  selector: 'app-teacher-management',
  templateUrl: './teacher-management.component.html',
  styleUrls: ['./teacher-management.component.css']
})
export class TeacherManagementComponent implements OnInit {

  teachers: Teacher[]
  page: number = 0;
  totalPagesCount: number;

  constructor(private userService: UserService, private cd: ChangeDetectorRef, private router: Router) { }

  ngOnInit(): void {
    this.fetchTeachers();

  }


  fetchTeachers() {
    this.userService
      .getTeachers(this.page)
      .subscribe((response: TeacherResponse) => {
        this.teachers = response.teachers
        this.totalPagesCount = response.pagesCount;
        this.cd.detectChanges();
      });



  }
  nextPage() {
    this.page = this.page + 1;
    this.fetchTeachers();
  }

  previousPage() {
    this.page = this.page - 1;
    this.fetchTeachers();
  }

  addTeacher() {
    this.router.navigate(['/add-teacher'])
  }

}
