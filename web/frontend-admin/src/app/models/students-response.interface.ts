import { Student } from './student.interface';

export interface StudentsResponse {
  students: Student[];
  pagesCount: number;
}
