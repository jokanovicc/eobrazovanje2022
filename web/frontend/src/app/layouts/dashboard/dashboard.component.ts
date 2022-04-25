import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.interface';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  user: User;
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authService.fetchCurrentUser().subscribe((user: any) => {
      this.user = user;
    });
    // this.authService.user$.subscribe((user: UserInfo) => {
    //   this.user = user;
    // });
  }

  updateEmployee() {
    this.router.navigate(['update-profile']);
  }
}
