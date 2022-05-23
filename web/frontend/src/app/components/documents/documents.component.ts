import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UploadFileService } from 'src/app/service/upload-file.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { saveAs } from 'file-saver';
import { User } from 'src/app/models/user.interface';
@Component({
  selector: 'app-documents',
  templateUrl: './documents.component.html',
  styleUrls: ['./documents.component.css']
})
export class DocumentsComponent implements OnInit {
  user: User;
  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';
  id: any;
  fileInfos: Observable<any>;
  authService: any;
  constructor(private uploadService: UploadFileService, private router: Router, private route: ActivatedRoute,) { }


  ngOnInit() {
    this.fileInfos = this.uploadService.getFiles();
  }
  selectFile(event: any) {
    this.selectedFiles = event.target.files;
  }

  fetchUser() {
    this.authService.fetchCurrentUser().subscribe((user: any) => {
      this.user = user;
    });
  }

  downloadFile(url: any): void {
    this.uploadService
      .download(url)
      .subscribe(blob => saveAs(blob, url));
  }

}
