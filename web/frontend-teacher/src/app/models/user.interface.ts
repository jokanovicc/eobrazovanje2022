import { UserRole } from './user-role.enum';

export interface User {
  id: number;
  name: string;
  address: string;
  jmbg: string;
  lastname: string;
  gender: string;
  username: string;
  email: string;
  role: string;
}
