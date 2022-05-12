import { Course } from './course.interface';
import { Teacher } from './teacher.interface';

export interface Performance {
  id: number;
  courseTeachers: Teacher[];
  course: Course;
}
