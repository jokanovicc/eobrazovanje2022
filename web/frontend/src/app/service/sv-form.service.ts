import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SvFormService {
  constructor(private http: HttpClient) {}

  setSVForm(data: JSON) {
    console.log(data);
    return this.http.post(
      environment.backend_endpoint + `students/svForm`,
      data
    );
  }
}
