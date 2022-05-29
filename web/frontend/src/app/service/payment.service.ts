import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { PaymentResposne } from '../models/paymentResposne.interface';

@Injectable({
  providedIn: 'root',
})
export class PaymentService {
  constructor(private http: HttpClient) {}

  getFinancialCard() {
    return this.http.get<any>(environment.backend_endpoint + `financialCards`);
  }

  getPayments(pageNumber?: number) {
    const options = {
      params: {
        page: pageNumber ? pageNumber : 0,
      },
    };
    return this.http.get<PaymentResposne>(
      environment.backend_endpoint + `payments/students`,
      options
    );
  }
}
