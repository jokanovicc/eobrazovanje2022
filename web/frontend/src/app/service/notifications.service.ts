import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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
}
