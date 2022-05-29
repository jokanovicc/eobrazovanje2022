import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentService } from 'src/app/services/payment.service';

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html',
  styleUrls: ['./payment-form.component.css'],
})
export class PaymentFormComponent implements OnInit {
  public paymentForm: FormGroup;
  public id: any;
  errorMsg = '';

  constructor(
    private formBuilder: FormBuilder,
    private paymentService: PaymentService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.paymentForm = this.formBuilder.group({
      text: ['', [Validators.required]],
      amount: ['', [Validators.required]],
    });
  }

  postPaymentForm() {
    this.paymentService
      .postPaymentForm(this.id, this.paymentForm.value)
      .subscribe({
        next: (x: any) => {
          this.router.navigate(['/payment/' + this.id]);
        },
        error: (err: any) => this.handleError(err),
      });
  }

  handleError(err: any) {
    console.log(err);
    if (err.error && err.error.responseMessage) {
      this.errorMsg = err.error.responseMessage;
    } else {
      this.errorMsg = 'Sva polja moraju biti pravilno popunjena!';
    }
  }
}
