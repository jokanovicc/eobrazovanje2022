import { Component, OnInit } from '@angular/core';
import { FinancialCard } from 'src/app/models/financial-card.interface';
import { PaymentService } from 'src/app/service/payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent implements OnInit {

  financialCard: FinancialCard;
  constructor(private paymentService: PaymentService) {}

  ngOnInit(): void {
    this.getFinancialCard();
  }

  getFinancialCard() {
    this.paymentService.getFinancialCard().subscribe({
      next: (x: any) => {
        this.financialCard = x;
      },
    });
  }
}
