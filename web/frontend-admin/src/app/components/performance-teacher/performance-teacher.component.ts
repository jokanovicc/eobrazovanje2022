import { HttpEventType, HttpResponse } from '@angular/common/http';
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
  public id: any;
  public performance: PerformanceTeacher;
  selectedFiles?: FileList;
  currentFile?: File;
  public messageAdded = '';
  public messageCreatedAndAdded = '';
  public messageAlreadyAdded = '';
  message = '';
  errorMsg = '';
  public response: AttendingResponseBukl = {
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
    this.id = this.route.snapshot.paramMap.get('id');
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
    this.selectedFiles = event.target.files;
  }

  upload(): void {
    if (this.validation()) {
      const file: File = this.selectedFiles.item(0);
      this.currentFile = file;
      this.insertStudentsService
        .uploadStudentsPefrormanceFile(this.currentFile, this.id)
        .subscribe({
          next: (x: any) => {
            this.handleSuccess(x);
            this.selectedFiles = undefined;
          },
          error: (err: any) => {
            this.handleError(err);
            this.currentFile = undefined;
          },
        });
    }
  }

  validation() {
    this.errorMsg = '';
    if (!this.selectedFiles) {
      this.errorMsg = 'Niste odabrali fajl';
      return false;
    }
    return true;
  }

  handleSuccess(x: any) {
    if (x.type === HttpEventType.UploadProgress) {
      this.fileUploadProgress(x);
    } else if (x instanceof HttpResponse && x.status == 201) {
      this.message = 'Studenti su uspešno dodati u bazu';
      this.response = x.body;
      this.messageBuilder();
      console.log(this.message);
    }
  }

  handleError(err: any) {
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
    this.messageAdded = 'Dodati: ' + this.response.added.toString();
    this.messageCreatedAndAdded =
      'Kreirani i dodati: ' + this.response.createdAndAdded.toString();
    this.messageAlreadyAdded =
      'Već pohađaju: ' + this.response.alreadyAdded.toString();
  }
}