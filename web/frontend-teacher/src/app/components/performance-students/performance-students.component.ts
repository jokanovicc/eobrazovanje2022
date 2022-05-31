import { HttpStatusCode } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Attending } from 'src/app/models/attending.interface';
import { CreateAttendingReq } from 'src/app/models/create-attending-req.interface';
import { AttendingService } from 'src/app/services/attending.service';

@Component({
  selector: 'app-performance-students',
  templateUrl: './performance-students.component.html',
  styleUrls: ['./performance-students.component.css'],
})
export class PerformanceStudentsComponent implements OnInit {
  public id: any;
  public attending: Attending[];
  public indexNumber: string;
  public data: CreateAttendingReq = { indexNumbers: [] };
  public page: number = 0;
  public totalPagesCount: number;
  errorMsg = '';

  constructor(
    private attendingService: AttendingService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.fetchById();
    console.log(this.attending);
  }

  fetchById() {
    this.attendingService
      .getStudentByPerformance(this.id, this.page)
      .subscribe((Response) => {
        this.attending = Response.attendings;
        this.totalPagesCount = Response.pageCount;
      });
  }

  remove(id: any) {
    this.attendingService.deleteStudentFromPerformance(this.id, id);
    window.location.reload();
  }

  addStudent() {
    this.data.indexNumbers = [];
    this.data.indexNumbers.push(this.indexNumber);
    this.attendingService.createAttending(this.data, this.id).subscribe({
      next: (x: any) => {
        window.location.reload();
      },
      error: (err: any) => {
        this.handleError(err);
      },
    });
  }

  nextPage() {
    this.page = this.page + 1;
    this.fetchById();
  }
  previousPage() {
    this.page = this.page - 1;
    this.fetchById();
  }

  handleError(err: any) {
    console.log(err);
    if (err.status === HttpStatusCode.BadRequest) {
      this.errorMsg = 'Student je već dodat';
    } else {
      this.errorMsg = 'Ne postoji student sa indekesom: ' + this.indexNumber;
    }
  }
}
