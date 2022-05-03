import { Course } from './course.interface';
import { ExamStatus } from './exam-status.enum';

export interface Exam {
  id: number;
  preExamDutyPoints: number;
  finalExamPoints: number;
  course: Course;
  grade: number;
  date: Date;
  classroom: string;
  status: string;
  examPeriod: string;
}
