import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Notification } from '../models/notification.interface';
import { environment } from 'src/environments/environment';
import { NotificationResponse } from '../models/notification-response.interface';

@Injectable({ providedIn: 'root' })
export class NotificationsService {
  constructor(private http: HttpClient) {}

  getNotifications(pageNumber?: number) {
    const options = {
      params: {
        page: pageNumber ? pageNumber : 0,
      },
    };

    return this.http.get<NotificationResponse>(
      environment.backend_endpoint + `notifications`,
      options
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
