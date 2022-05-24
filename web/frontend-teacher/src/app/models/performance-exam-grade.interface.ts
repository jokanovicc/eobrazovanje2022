import { ExamPeriod } from './exam-period.interface';
import { Performance } from './performance.interface';

export interface PerformanceExamGrade {
  id: number;
  performance: Performance;
  date: Date;
  examPeriod: ExamPeriod;
  classroom: string;
  status: String;
}
