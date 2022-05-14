import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SetFirstPasswordService } from 'src/app/service/set-first-password.service';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
@Component({
  selector: 'app-set-first-password',
  templateUrl: './set-first-password.component.html',
  styleUrls: ['./set-first-password.component.css'],
})
export class SetFirstPasswordComponent implements OnInit {
  public setPasswordForm: FormGroup;
  token: string;
  public message = '';
  errorMsg = '';

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private setFirstPasswordService: SetFirstPasswordService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.setPasswordForm = this.formBuilder.group({
      password: ['', [Validators.required]],
    });
    this.route.queryParams.subscribe((params) => {
      this.token = params['token'];
    });
  }

  setPassword() {
    this.setFirstPasswordService
      .setPassword(this.setPasswordForm.get('password')?.value, this.token)
      .subscribe({
        next: (x: any) => {
          this.router.navigate(['']);
        },
        error: (err: any) => {
          console.log(err);
          if (err.error && err.error.responseMessage) {
            this.errorMsg = err.error.responseMessage;
          } else {
            this.errorMsg = 'Greška!';
          }
        },
      });
  }
}
