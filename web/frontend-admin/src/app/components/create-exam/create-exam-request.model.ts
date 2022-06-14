import { ExamPeriod } from '../../models/exam-period.interface';
import { Performance } from '../../models/performance.interface';

export interface CreateExamRequest {
  examPeriodId: number;
  performanceId: number;
  date: Date;
  classroom: string;
}
