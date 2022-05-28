import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import jwtDecode from 'jwt-decode';
import { User } from '../models/user.interface';
import { isNull } from '@angular/compiler/src/output/output_ast';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private currentUser: User;
  public user$: BehaviorSubject<User> = new BehaviorSubject(null as any);
  public token$: BehaviorSubject<string> = new BehaviorSubject(null as any);

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    return this.http
      .post(
        environment.backend_endpoint + `users/login`,
        { username, password },
        { responseType: 'text' }
      )
      .pipe(
        tap((token: any) => {
          if(this.getRoleWithProps(token) == "ROLE_STUDENT"){
              this.token$.next(token);
              this.saveToken(token);
          }else{
            Swal.fire(
              "Greška!",
              "Nemate adekvatnu ulogu za pristup ovom servisu",
              "error"
            )
          }
        })
      );
  }

  fetchCurrentUser() {
    return this.http.get<User>(environment.backend_endpoint + `users`).pipe(
      tap((user: any) => {
        this.currentUser = user;
        this.user$.next(user);
      })
    );
  }

  getCurrentUserInfo() {
    return this.currentUser;
  }

  updateUserProfile(id: number, userDTO: any) {
    return this.http.put(environment.backend_endpoint + 'users', userDTO);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  removeToken() {
    return localStorage.removeItem('token');
  }

  private saveToken(token: string) {
    if(jwtDecode(token))
    localStorage.setItem('token', token);
  }

  decodeToken(token: string): any {
    try {
      return jwtDecode(token);
    } catch (error) {
      return null;
    }
  }

  getRole() {
    const token = this.getToken();
    const decoded_token = token ? this.decodeToken(token) : null;
    if (decoded_token) {
      return decoded_token.role.authority;
    } else {
      return null;
    }
  }

  getRoleWithProps(token:string) {
    const decoded_token = token ? this.decodeToken(token) : null;
    if (decoded_token) {
      return decoded_token.role.authority;
    } else {
      return null;
    }
  }


  logout() {
    localStorage.clear();
    this.currentUser = null;
    this.user$.next(null);
    this.token$.next(null);
  }
}
