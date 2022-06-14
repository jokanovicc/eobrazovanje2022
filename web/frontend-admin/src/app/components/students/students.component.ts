import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from 'src/app/models/student.interface';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css'],
})
export class StudentsComponent implements OnInit {
  students: Student[];
  page: number = 0;
  totalPagesCount: number;

  constructor(
    private userService: UserService,
    private cd: ChangeDetectorRef,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.fetchStudents();
  }

  fetchStudents() {
    this.userService.getStudents(this.page).subscribe((response: any) => {
      this.students = response.students;
      this.totalPagesCount = response.pagesCount;
      this.cd.detectChanges();
    });
  }

  nextPage() {
    this.page = this.page + 1;
    this.fetchStudents();
  }

  previousPage() {
    this.page = this.page - 1;
    this.fetchStudents();
  }

  getCard(id: any) {
    this.router.navigate(['/studentProfile/' + id]);
  }
}
