export class Course {
  private _id: number;
  private _sylabus: string;
  private _name: number;
  private _ESPB: string;

  constructor(obj: any) {
    this._id = obj && obj.id;
    this._sylabus = obj && obj.sylabus;
    this._name = obj && obj.name;
    this._ESPB = obj && obj.ESPB;
  }

  public get ESPB(): string {
    return this._ESPB;
  }
  public set ESPB(value: string) {
    this._ESPB = value;
  }

  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }

  public get sylabus(): string {
    return this._sylabus;
  }
  public set sylabus(value: string) {
    this._sylabus = value;
  }

  public get name(): number {
    return this._name;
  }
  public set name(value: number) {
    this._name = value;
  }
}
