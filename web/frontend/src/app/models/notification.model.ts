import { Teacher } from './teacher.model';

export class Notification {
  private _id: number;
  private _message: string;
  private _coursePerformance: Performance;
  private _teacher: Teacher;

  constructor(obj: any) {
    this._id = obj && obj.id;
    this._message = obj && obj.message;
    this._coursePerformance = obj && obj.coursePerformance;
    this._teacher = obj && obj.teacher;
  }

  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }

  public get message(): string {
    return this._message;
  }
  public set message(value: string) {
    this._message = value;
  }

  public get coursePerformance(): Performance {
    return this._coursePerformance;
  }
  public set coursePerformance(value: Performance) {
    this._coursePerformance = value;
  }

  public get teacher(): Teacher {
    return this._teacher;
  }
  public set teacher(value: Teacher) {
    this._teacher = value;
  }
}
