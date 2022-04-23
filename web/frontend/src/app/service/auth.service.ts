import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { Observable, tap } from 'rxjs';
import { UserInfo } from '../layouts/dashboard/dashboard.component';


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
    return this.http.get<UserInfo>(`http://localhost:8080/api/users`);

  }

  getCurrentUserInfo() {
    return this.currentUser;
  }

  updateUserProfile(id:number, userDTO:any){
    return this.http.put("http://localhost:8080/api/users", userDTO);
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
