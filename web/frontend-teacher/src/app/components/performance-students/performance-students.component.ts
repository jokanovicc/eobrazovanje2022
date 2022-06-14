import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Attending } from 'src/app/models/attending.interface';
import { CreateAttendingReq } from 'src/app/models/create-attending-req.interface';
import { AttendingService } from 'src/app/services/attending.service';

@Component({
  selector: 'app-performance-students',
  templateUrl: './performance-students.component.html',
  styleUrls: ['./performance-students.component.css'],
})
export class PerformanceStudentsComponent implements OnInit {
  id: number;
  attending: Attending[];
  indexNumber: string;
  data: CreateAttendingReq = { indexNumbers: [] };
  page: number = 0;
  totalPagesCount: number;
  errorMsg = '';

  constructor(
    private attendingService: AttendingService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.fetchById();
  }

  fetchById() {
    this.attendingService
      .getStudentByPerformance(this.id, this.page)
      .subscribe((Response) => {
        this.attending = Response.attendings;
        this.totalPagesCount = Response.pageCount;
      });
  }

  remove(id: number) {
    this.attendingService.deleteStudentFromPerformance(this.id, id);
    window.location.reload();
  }

  addStudent() {
    this.data.indexNumbers = [];
    this.data.indexNumbers.push(this.indexNumber);
    this.attendingService.createAttending(this.data, this.id).subscribe({
      next: () => {
        window.location.reload();
      },
      error: (err: HttpErrorResponse) => {
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

  handleError(err: HttpErrorResponse) {
    if (err.status === HttpStatusCode.BadRequest) {
      this.errorMsg = 'Student je već dodat';
    } else {
      this.errorMsg = 'Ne postoji student sa indekesom: ' + this.indexNumber;
    }
  }
}
