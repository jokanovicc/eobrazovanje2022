import { Performance } from './performance.interface';
import { Student } from './student.interface';

export interface Attending {
  id: number;
  performance: Performance;
  student: Student;
}
