import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UploadFileService {
  private baseUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }


  getFiles(): Observable<any> {
    return this.http.get(`${this.baseUrl}/student-files`);
  }

  download(link: string | undefined): Observable<Blob> {
    return this.http.get(link, {
      responseType: 'blob'
    });
  }
  
}