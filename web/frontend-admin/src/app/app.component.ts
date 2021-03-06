import { Component, OnInit } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'frontend';

  public userLoggedIn: boolean;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.token$.subscribe((token: string) => {
      if (!token) {
        this.userLoggedIn = !!localStorage.getItem('token');
        return;
      }
      this.userLoggedIn = !!token;
    });
  }
}
