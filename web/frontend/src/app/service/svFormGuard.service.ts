import { Injectable } from '@angular/core';
import { Routes, CanActivate, Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Student } from '../models/student.interface';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { StudentService } from './student.service';
@Injectable({
  providedIn: 'root',
})
export class svFormGuard implements CanActivate {
  student: Student;
  completedSV: boolean;
  constructor(public studentService: StudentService, public router: Router) {}

  canActivate(): boolean {
    this.studentService.fetchCurrentStudent().subscribe({
      next: (student: any) => {
        this.student = student;
        this.completedSV = this.student.completedSV;
        if (!this.completedSV) {
          this.router.navigate(['/svForm']);
          return false;
        }
      },
    });
    return true;
  }
}
