import { Course } from './course.model';
import { Teacher } from './teacher.model';

export class Performance {
  private _id: number;
  private _courseTeachers: Teacher[];
  private _course: Course;

  constructor(obj: any) {
    this._id = obj && obj.id;
    this._courseTeachers = obj && obj.courseTeachers;
    this._course = obj && obj.course;
  }

  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }

  public get courseTeachers(): Teacher[] {
    return this._courseTeachers;
  }
  public set courseTeachers(value: Teacher[]) {
    this._courseTeachers = value;
  }

  public get course(): Course {
    return this._course;
  }
  public set course(value: Course) {
    this._course = value;
  }
}
