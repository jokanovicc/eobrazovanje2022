import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NotificationResponse } from 'src/app/models/notification-response.interface';
import { Notification } from 'src/app/models/notification.interface';
import { User } from 'src/app/models/user.interface';
import { AuthService } from 'src/app/services/auth.service';
import { NotificationsService } from 'src/app/services/notifications.service';

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
    private authService: AuthService,
    private modalService: NgbModal,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.fetchNotifications();
    this.getCurrentUser();
  }

  fetchNotifications() {
    this.notificationService
      .getNotifications(this.page)
      .subscribe((response: NotificationResponse) => {
        this.notifications = response.notifications;
        this.totalPagesCount = response.pagesCount;
        this.cd.detectChanges();
      });
  }

  getCurrentUser() {
    this.authService.fetchCurrentUser().subscribe((user) => {
      this.currentUser = user;
    });
  }

  nextPage() {
    this.page = this.page + 1;
    this.fetchNotifications();
  }

  previousPage() {
    this.page = this.page - 1;
    this.fetchNotifications();
  }

  delete(notificationId: number) {
    this.notificationService.delete(notificationId).subscribe(() => {
      this.fetchNotifications();
    });
  }

  openModal(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' });
  }
}
