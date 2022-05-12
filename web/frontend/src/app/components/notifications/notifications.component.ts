import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { NotificationResponse } from 'src/app/models/notification-response.interface';
import { Notification } from 'src/app/models/notification.interface';
import { User } from 'src/app/models/user.interface';
import { AuthService } from 'src/app/service/auth.service';
import { NotificationsService } from 'src/app/service/notifications.service';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css'],
})
export class NotificationsComponent implements OnInit {
  public notifications: Notification[];
  public currentUser: User;

  public page: number = 0;
  public totalPagesCount: number;

  constructor(
    private notificationService: NotificationsService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.fetchNotifications();
    this.getCurrentUser();
  }

  fetchNotifications(pageNumber?: number) {
    this.notificationService
      .getNotifications(pageNumber)
      .subscribe((response: NotificationResponse) => {
        this.notifications = response.notifications;
        this.totalPagesCount = response.pagesCount;
      });
  }

  getCurrentUser() {
    this.authService.fetchCurrentUser().subscribe((user) => {
      this.currentUser = user;
    });
  }

  nextPage() {
    this.page = this.page + 1;
    this.fetchNotifications(this.page);
  }

  previousPage() {
    this.page = this.page - 1;
    this.fetchNotifications(this.page);
  }

  delete(notificationId: number) {
    this.notificationService.delete(notificationId).subscribe(() => {
      this.notifications.map(
        (notification) => notification.id != notificationId
      );
    });
  }
}
