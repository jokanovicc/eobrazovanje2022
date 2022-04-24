import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

export interface UserInfo {
  id: number;
  name: string;
  address: string;
  jmbg: string;
  lastname: string;
  gender: string;
  username: string;
  email: string;
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  user: UserInfo;
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authService.fetchCurrentUser().subscribe((user: UserInfo) => {
      this.user = user;
    });
  }

  // getUser() {
  //   this.authService.fetchCurrentUser().subscribe((Response) => {
  //     this.user = Response;
  //     console.log(this.user);
  //   });
  // }

  updateEmployee() {
    this.router.navigate(['update-profile']);
  }
}
