import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest } from '@angular/common/http';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root',
})
export class SetFirstPasswordService {
  constructor(private http: HttpClient) {}

  setPassword(password: string, token: string) {
    return this.http.post(
      environment.backend_endpoint + `students/firstPassword`,
      { token, password }
    );
  }
}
