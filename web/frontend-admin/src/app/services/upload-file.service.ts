import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UploadFileService {
  private baseUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }


  
  upload(file: File, id:any): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest('POST', `${this.baseUrl}/upload/${id}`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }
  getFiles(id:any): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}/files`);
  }

  download(link: string | undefined): Observable<Blob> {
    return this.http.get(link, {
      responseType: 'blob'
    });
  }
  
}