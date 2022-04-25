import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Notification } from '../models/notification.interface';

@Injectable({ providedIn: 'root' })
export class NotificationsService {
  constructor(private http: HttpClient) {}

  getNotifications() {
    return this.http.get<Notification[]>(
      `http://localhost:8080/api/notifications`
    );
  }

  sendNotification(message: string, performanceId: number) {
    return this.http.post(`http://localhost:8080/api/notifications`, {
      performanceId,
      message,
    });
  }

  delete(notificationId: number) {
    return this.http.delete(
      `http://localhost:8080/api/notifications/${notificationId}`
    );
  }
}
