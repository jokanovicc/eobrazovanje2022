import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Payment } from 'src/app/models/payment.interface';
import { PaymentService } from 'src/app/services/payment.service';

@Component({
  selector: 'app-student-payments-table',
  templateUrl: './student-payments-table.component.html',
  styleUrls: ['./student-payments-table.component.css'],
})
export class StudentPaymentsTableComponent implements OnInit {
  payments: Payment[];
  page: number = 0;
  totalPagesCount: number;
  id: number;

  constructor(
    private paymentService: PaymentService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getPayments();
  }

  getPayments() {
    this.paymentService.getStudentPayments(this.id, this.page).subscribe({
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
