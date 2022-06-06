import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SetFirstPasswordService } from 'src/app/service/set-first-password.service';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { SvFormService } from 'src/app/service/sv-form.service';

@Component({
  selector: 'app-sv-form',
  templateUrl: './sv-form.component.html',
  styleUrls: ['./sv-form.component.css'],
})
export class SvFormComponent implements OnInit {

  svForm: FormGroup;
  token: string;
  message = '';
  errorMsg = '';
  genders: string[] = ['muški', 'ženski'];

  constructor(
    private formBuilder: FormBuilder,
    private svFormService: SvFormService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.svForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      jmbg: ['', [Validators.required]],
      address: ['', [Validators.required]],
      gender: ['', [Validators.required]],
      index: ['', [Validators.required]],
    });
  }

  get gender() {
    return this.svForm.get('gender');
  }

  changeGender(e: any) {
    this.gender?.setValue(e.target.value, {
      onlySelf: true,
    });
  }

  setSVForm() {
    this.svFormService.setSVForm(this.svForm.value).subscribe({
      next: (x: any) => {
        this.router.navigate(['']);
      },
      error: (err: any) => this.handleError(err),
    });
  }

  handleError(err: any) {
    if (err.error && err.error.responseMessage) {
      this.errorMsg = err.error.responseMessage;
    } else {
      this.errorMsg = 'Sva polja moraju biti pravilno popunjena!';
    }
  }
}
