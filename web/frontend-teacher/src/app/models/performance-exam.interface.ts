import { ExamPeriod } from './exam-period.interface';
import { Performance } from './performance.interface';

export interface PerformanceExam {
  id: number;
  performance: Performance;
  date: Date;
  name: string;
  examPeriod: ExamPeriod;
  classroom: string;
}
