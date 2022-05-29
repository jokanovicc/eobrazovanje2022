import { Student } from './student.interface';

export interface Payment {
  id: number;
  student: Student;
  amount: number;
  text: string;
  accountNumber: string;
  paymentDate: Date;
}
