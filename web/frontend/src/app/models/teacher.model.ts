export class Teacher {
  private _id: number;
  private _name: string;
  private _teacherRole: string;
  private _username: string;

  constructor(obj: any) {
    this._id = obj && obj.id;
    this._teacherRole = obj && obj.teacherRole;
    this._name = obj && obj.name;
    this._username = obj && obj.username;
  }

  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }

  public get name(): string {
    return this._name;
  }
  public set name(value: string) {
    this._name = value;
  }

  public get teacherRole(): string {
    return this._teacherRole;
  }
  public set teacherRole(value: string) {
    this._teacherRole = value;
  }

  public get username(): string {
    return this._username;
  }
  public set username(value: string) {
    this._username = value;
  }
}
