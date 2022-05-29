import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  status: boolean = false;
  token: string | null;

  constructor(private authService: AuthService, private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;

  }

  ngOnInit(): void {
    this.authService.token$.subscribe((token: string) => {
      if (!token) {
        this.token = localStorage.getItem('token');
        return;
      }
      this.token = token;
    });
  }
  clickEvent() {
    console.log('hoho');
    this.status = !this.status;
  }


  logoutButton() {
    this.authService.logout();
    this.token = null;
    this.router.navigate(['']);
  }

}
