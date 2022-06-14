import {
  HttpErrorResponse,
  HttpEventType,
  HttpResponse,
} from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AttendingResponseBukl } from 'src/app/models/attending-bulk-response.interface';
import {
  PerformanceTeacher,
  TeacherPerformance,
} from 'src/app/models/performance-teacher.interface';
import { InsertStudentsService } from 'src/app/services/insert-students.service';
import { PerformanceService } from 'src/app/services/performance.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-performance-teacher',
  templateUrl: './performance-teacher.component.html',
  styleUrls: ['./performance-teacher.component.css'],
})
export class PerformanceTeacherComponent implements OnInit {
  id: number;
  performance: PerformanceTeacher;
  currentFile: File;
  messageAdded = '';
  messageCreatedAndAdded = '';
  messageAlreadyAdded = '';
  message = '';
  errorMsg = '';
  response: AttendingResponseBukl = {
    added: [],
    createdAndAdded: [],
    alreadyAdded: [],
  };

  constructor(
    private performanceService: PerformanceService,
    private route: ActivatedRoute,
    private router: Router,
    private insertStudentsService: InsertStudentsService
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.fetchById();
  }

  fetchById() {
    this.performanceService.getById(this.id).subscribe((Response) => {
      this.performance = Response;
      console.log(Response);
    });
  }

  remove(teachId: any) {
    alert('klik');
    this.performanceService.deleteTeacherFromPerformance(this.id, teachId);
    Swal.fire('Uspešno!', 'Nastavnik je uklonjen sa predmeta.', 'success').then(
      () => location.reload()
    );
  }

  addNew(id: any) {
    this.router.navigate(['/add-to-performance/' + this.id]);
  }

  selectFile(event: any) {
    this.currentFile = event.target.files.item[0];
  }

  upload(): void {
    if (this.validation()) {
      this.insertStudentsService
        .uploadStudentsPefrormanceFile(this.currentFile, this.id)
        .subscribe({
          next: (response: any) => {
            this.handleSuccess(response);
            this.currentFile = undefined;
          },
          error: (err: HttpErrorResponse) => {
            this.handleError(err);
            this.currentFile = undefined;
          },
        });
    }
  }

  validation() {
    this.errorMsg = '';
    if (!this.currentFile) {
      this.errorMsg = 'Niste odabrali fajl';
      return false;
    }
    return true;
  }

  handleSuccess(response: any) {
    if (response.type === HttpEventType.UploadProgress) {
      this.fileUploadProgress(response);
    } else if (response instanceof HttpResponse && response.status == 201) {
      this.message = 'Studenti su uspešno dodati u bazu';
      this.response = response.body;
      this.messageBuilder();
    }
  }

  handleError(err: HttpErrorResponse) {
    console.log(err);
    if (err.error && err.error.responseMessage) {
      this.errorMsg = err.error.responseMessage;
    } else {
      this.errorMsg = 'Greška!';
    }
  }

  fileUploadProgress(x: any) {
    this.message =
      'Slanje fajla: ' +
      Math.round((100 * x.loaded) / x.total) +
      '%' +
      '. Sačekajte';
  }

  messageBuilder() {
    this.messageAdded = `Dodati: ${this.response.added.toString()}`;
    this.messageCreatedAndAdded = `Kreirani i dodati: ${this.response.createdAndAdded.toString()}`;
    this.messageAlreadyAdded = `Već pohađaju: ${this.response.alreadyAdded.toString()}`;
  }
}
