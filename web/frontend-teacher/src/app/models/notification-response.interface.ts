import { Notification } from './notification.interface';

export interface NotificationResponse {
  notifications: Notification[];
  pagesCount: number;
}
