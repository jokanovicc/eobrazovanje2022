import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import jwtDecode from 'jwt-decode';
import { User } from '../models/models.interface';
import { isNull } from '@angular/compiler/src/output/output_ast';
import Swal from 'sweetalert2';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private currentUser: User | null | undefined;
  public user$: BehaviorSubject<User> = new BehaviorSubject(null as any);
  public token$: BehaviorSubject<string> = new BehaviorSubject(null as any);

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
          if(this.getRoleWithProps(token) == "ROLE_ADMIN"){
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

  getRoleWithProps(token:string) {
    const decoded_token = token ? this.decodeToken(token) : null;
    if (decoded_token) {
      return decoded_token.role.authority;
    } else {
      return null;
    }
  }

  fetchCurrentUser() {
    return this.http.get<User>(`http://localhost:8080/api/users`).pipe(
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
    return this.http.put('http://localhost:8080/api/users', userDTO);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  removeToken() {
    return localStorage.removeItem('token');
  }

  private saveToken(token: string) {
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

   didTokenExpire() {
    const token = this.getToken();
    const decodedToken = token ? this.decodeToken(token) : null;
    return decodedToken ? decodedToken.exp_date < Date.now() : null;
}

  logout() {
    this.currentUser = null;
    this.user$.next(null!);
    localStorage.clear();
  }
}
