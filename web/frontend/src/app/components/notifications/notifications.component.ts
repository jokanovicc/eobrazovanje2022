import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
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

  constructor(
    private notificationService: NotificationsService,
    private cd: ChangeDetectorRef,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.fetchNotifications();
    this.getCurrentUser();
  }

  fetchNotifications() {
    this.notificationService
      .getNotifications()
      .subscribe((response: Notification[]) => {
        this.notifications = response;
      });
  }

  getCurrentUser() {
    this.authService.fetchCurrentUser().subscribe((user) => {
      this.currentUser = user;
    });
  }

  delete(notificationId: number) {
    this.notificationService.delete(notificationId).subscribe(() => {
      this.notifications.map(
        (notification) => notification.id != notificationId
      );
    });
  }
}
