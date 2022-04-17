import { UserRole } from './user-role.enum';

export interface UserInterface {
  id: number;
  email: string;
  name: string;
  role: UserRole;
}

export class User {
  private _id: number;
  private _email: string;
  private _name: string;
  private _role: UserRole;

  constructor(obj: any) {
    this._id = obj && obj.id;
    this._email = obj && obj.email;
    this._name = obj && obj.name;
    this._role = obj && obj.role;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get role(): UserRole {
    return this._role;
  }

  set role(value: UserRole) {
    this._role = value;
  }
}
