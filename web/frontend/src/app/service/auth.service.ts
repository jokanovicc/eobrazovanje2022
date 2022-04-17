import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { tap } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private currentUser: User;

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    return this.http
      .post(
        `http://localhost:8080/api/users/login`,
        { username, password },
        { responseType: 'text' }
      )
      .pipe(
        tap((token: any) => {
          this.saveToken(token);
        })
      );
  }

  fetchCurrentUser() {
    return this.http.get('http://localhost:8080/api/users').pipe(
      tap((response: any) => {
        this.currentUser = response;
      })
    );
  }

  getCurrentUserInfo() {
    return this.currentUser;
  }

  getToken() {
    return localStorage.getItem('token');
  }

  private saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  logout() {
    this.currentUser = new User(null);
    localStorage.clear();
  }
}
