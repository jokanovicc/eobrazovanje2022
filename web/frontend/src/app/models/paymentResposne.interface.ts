import { Payment } from './payment.interface';

export interface PaymentResposne {
  payments: Payment[];
  pagesCount: number;
}
