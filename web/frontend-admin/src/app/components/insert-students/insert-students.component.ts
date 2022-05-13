import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { InsertStudentsService } from 'src/app/services/insert-students.service';

@Component({
  selector: 'app-insert-students',
  templateUrl: './insert-students.component.html',
  styleUrls: ['./insert-students.component.css'],
})
export class InsertStudentsComponent implements OnInit {
  selectedFiles?: FileList;
  currentFile?: File;
  public message = '';
  errorMsg = '';

  constructor(private insertStudentsService: InsertStudentsService) {}

  ngOnInit() {}

  selectFile(event: any) {
    this.selectedFiles = event.target.files;
  }

  upload(): void {
    this.errorMsg = '';

    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.currentFile = file;

        this.insertStudentsService.uploadFile(this.currentFile).subscribe({
          next: (x: any) => {
            if (x.type === HttpEventType.UploadProgress) {
              console.log(
                'Upload progress: ' +
                  Math.round((100 * x.loaded) / x.total) +
                  '%'
              );
            } else if (x instanceof HttpResponse && x.status == 201) {
              this.message = 'Studenti su uspešno dodati u bazu';
            }
          },
          error: (err: any) => {
            console.log(err);
            if (err.error && err.error.responseMessage) {
              this.errorMsg = err.error.responseMessage;
            } else {
              this.errorMsg = 'Greška prilikom slanja fajla!';
            }

            this.currentFile = undefined;
          },
        });
      }

      this.selectedFiles = undefined;
    }
  }
}
