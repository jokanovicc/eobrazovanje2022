import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { NotificationsService } from 'src/app/service/notifications.service';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css'],
})
export class NotificationsComponent implements OnInit {
  public notifications: Notification[];

  constructor(
    private notificationService: NotificationsService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.fetchNotifications();
  }

  fetchNotifications() {
    this.notificationService
      .getNotifications()
      .subscribe((response: Notification[]) => {
        this.notifications = response;
        console.log(response);
        console.log(this.notifications);
        this.cd.detectChanges();
      });
  }
}
