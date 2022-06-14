import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FinancialCard } from 'src/app/models/financial-card.interface';
import { PaymentService } from 'src/app/services/payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent implements OnInit {

  financialCard: FinancialCard;
  id: number;

  constructor(
    private paymentService: PaymentService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getFinancialCard();
  }

  getFinancialCard() {
    this.paymentService.getFinancialCard(this.id).subscribe({
      next: (x: any) => {
        this.financialCard = x;
      },
    });
  }

  addPayment() {
    this.router.navigate(['/addPayment/' + this.id]);
  }
}
