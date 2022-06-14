import { Component, OnInit } from '@angular/core';
import { Payment } from 'src/app/models/payment.interface';
import { PaymentResposne } from 'src/app/models/paymentResposne.interface';
import { PaymentService } from 'src/app/service/payment.service';

@Component({
  selector: 'app-payment-table',
  templateUrl: './payment-table.component.html',
  styleUrls: ['./payment-table.component.css'],
})
export class PaymentTableComponent implements OnInit {
  payments: Payment[];
  page: number = 0;
  totalPagesCount: number;

  constructor(private paymentService: PaymentService) {}

  ngOnInit(): void {
    this.getPayments();
  }

  getPayments() {
    this.paymentService.getPayments(this.page).subscribe({
      next: (response: PaymentResposne) => {
        this.payments = response.payments;
        this.totalPagesCount = response.pageCount;
      },
    });
  }

  nextPage() {
    this.page = this.page + 1;
    this.getPayments();
  }
  previousPage() {
    this.page = this.page - 1;
    this.getPayments();
  }
}
