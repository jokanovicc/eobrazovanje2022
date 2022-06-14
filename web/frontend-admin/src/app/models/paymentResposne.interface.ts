import { Payment } from './payment.interface';

export interface PaymentResposne {
  payments: Payment[];
  pageCount: number;
}
