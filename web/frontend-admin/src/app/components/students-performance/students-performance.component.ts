import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AttendingResponse } from 'src/app/models/attending-pageable.interface';
import { Attending } from 'src/app/models/attending.interface';
import { PerformanceService } from 'src/app/services/performance.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-students-performance',
  templateUrl: './students-performance.component.html',
  styleUrls: ['./students-performance.component.css'],
})
export class StudentsPerformanceComponent implements OnInit {
  id: number;
  attending: Attending[];
  page: number = 0;
  totalPagesCount: number;

  constructor(
    private performanceService: PerformanceService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.fetchById();
  }

  fetchById() {
    this.performanceService
      .getStudentByPerformance(this.id)
      .subscribe((response: AttendingResponse) => {
        this.attending = response.attendings;
        this.totalPagesCount = response.pageCount;
      });
  }

  remove(id: number) {
    this.performanceService.deleteStudentFromPerformance(this.id, id);
    Swal.fire('Uspešno!', 'Student je uklonjen sa predmeta.', 'success').then(
      () => location.reload()
    );
  }

  nextPage() {
    this.page = this.page + 1;
    this.fetchById();
  }
  previousPage() {
    this.page = this.page - 1;
    this.fetchById();
  }
}
