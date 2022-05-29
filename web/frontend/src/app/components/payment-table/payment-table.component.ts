import { Component, OnInit } from '@angular/core';
import { Payment } from 'src/app/models/payment.interface';
import { PaymentService } from 'src/app/service/payment.service';

@Component({
  selector: 'app-payment-table',
  templateUrl: './payment-table.component.html',
  styleUrls: ['./payment-table.component.css'],
})
export class PaymentTableComponent implements OnInit {
  public payments: Payment[];
  public page: number = 0;
  public totalPagesCount: number;
  public id: any;

  constructor(private paymentService: PaymentService) {}

  ngOnInit(): void {
    this.getPayments();
  }

  getPayments() {
    this.paymentService.getPayments().subscribe({
      next: (x: any) => {
        this.payments = x.payments;
        this.totalPagesCount = x.pageCount;
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
