import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css'],
})
export class NavigationBarComponent implements OnInit {
  token: string | null;

  constructor(private authService: AuthService, private router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.authService.token$.subscribe((token: string) => {
      this.token = token;
    });
  }

  logoutButton() {
    this.authService.removeToken();
    this.token = null;
    this.router.navigate(['']);
  }
}

