import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { PaymentResposne } from '../models/paymentResposne.interface';

@Injectable({
  providedIn: 'root',
})
export class PaymentService {
  constructor(private http: HttpClient) {}

  getFinancialCard(studentId: any) {
    return this.http.get<any>(
      environment.backend_endpoint + `financialCards/${studentId}`
    );
  }

  getStudentPayments(studentId: any, pageNumber?: number) {
    const options = {
      params: {
        page: pageNumber ? pageNumber : 0,
      },
    };
    return this.http.get<PaymentResposne>(
      environment.backend_endpoint + `payments/students/${studentId}`,
      options
    );
  }

  postPaymentForm(studentId: any, data: any) {
    return this.http.post(
      environment.backend_endpoint + `payments/students/${studentId}`,
      data
    );
  }
}
