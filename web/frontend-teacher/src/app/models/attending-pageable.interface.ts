import { Attending } from './attending.interface';

export interface AttendingResponse {
  attendings: Attending[];
  pageCount: number;
}
