import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Notification } from '../models/notification.interface';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class NotificationsService {
  constructor(private http: HttpClient) {}

  getNotifications() {
    return this.http.get<Notification[]>(
      environment.backend_endpoint + `notifications`
    );
  }

  sendNotification(message: string, performanceId: number) {
    return this.http.post(environment.backend_endpoint + `notifications`, {
      performanceId,
      message,
    });
  }

  delete(notificationId: number) {
    return this.http.delete(
      environment.backend_endpoint + `notifications/${notificationId}`
    );
  }
}
