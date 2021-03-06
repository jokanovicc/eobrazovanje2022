import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit, OnDestroy {
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private cd: ChangeDetectorRef
  ) { }

  loginForm: FormGroup;
  wrongCredentials: boolean;
  destroy$ = new Subject();

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }

  public login() {
    console.log('Called!');
    this.authService
      .login(
        this.loginForm.get('username')?.value,
        this.loginForm.get('password')?.value
      )
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: this.onAuthSuccess,
        error: this.onAuthError,
      });
    this.cd.detectChanges();
  }

  private onAuthSuccess = (response: string) => {
    this.authService
      .fetchCurrentUser()
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => {
        this.router.navigate(['/dashboard']);
      });
  };

  private onAuthError = (error: HttpErrorResponse) => {
    if (error.status === HttpStatusCode.BadRequest) {
      this.wrongCredentials = true;
      this.cd.detectChanges();
    }
  };
}

