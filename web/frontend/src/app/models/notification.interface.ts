import { Teacher } from './teacher.interface';
import { Performance } from './performance.interface';

export interface Notification {
  id: number;
  message: string;
  coursePerformance: Performance;
  teacher: Teacher;
}
