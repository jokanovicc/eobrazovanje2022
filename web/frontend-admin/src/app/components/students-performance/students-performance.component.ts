import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Attending } from 'src/app/models/attending.interface';
import { PerformanceService } from 'src/app/services/performance.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-students-performance',
  templateUrl: './students-performance.component.html',
  styleUrls: ['./students-performance.component.css'],
})
export class StudentsPerformanceComponent implements OnInit {
  public id: any;
  public attending: Attending[];
  public page: number = 0;
  public totalPagesCount: number;

  constructor(
    private performanceService: PerformanceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.fetchById();
  }

  fetchById() {
    this.performanceService
      .getStudentByPerformance(this.id)
      .subscribe((Response) => {
        this.attending = Response.attendings;
        this.totalPagesCount = Response.pageCount;
        console.log(Response);
      });
  }

  remove(id: any) {
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
