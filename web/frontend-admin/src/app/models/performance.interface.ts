import { Course } from './course.interface';
import { Teacher } from './teacher.interface';

export interface Performance {
  id: number;
  schoolYear: string;
  courseTeachers: Teacher[];
  course: Course;
}
