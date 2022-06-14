import {
  HttpErrorResponse,
  HttpEventType,
  HttpResponse,
} from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { InsertStudentsService } from 'src/app/services/insert-students.service';

@Component({
  selector: 'app-insert-students',
  templateUrl: './insert-students.component.html',
  styleUrls: ['./insert-students.component.css'],
})
export class InsertStudentsComponent implements OnInit {
  currentFile: File;
  message = '';
  errorMsg = '';

  constructor(private insertStudentsService: InsertStudentsService) {}

  ngOnInit() {}

  selectFile(event: any) {
    this.currentFile = event.target.files.item[0];
  }

  upload(): void {
    if (this.validation()) {
      this.insertStudentsService.uploadFile(this.currentFile).subscribe({
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

  fileUploadProgress(response: any) {
    this.message =
      'Slanje fajla: ' +
      Math.round((100 * response.loaded) / response.total) +
      '%' +
      '. Sačekajte';
  }
}
