import { Component, OnInit, Output } from '@angular/core';
import { Performance } from 'src/app/models/performance.interface';
import { EventEmitter } from '@angular/core';
import { NotificationsService } from 'src/app/services/notifications.service';
import { PerformanceService } from 'src/app/services/performance.service';

@Component({
  selector: 'add-notification-modal',
  templateUrl: './add-notification-modal.component.html',
  styleUrls: ['./add-notification-modal.component.css'],
})
export class AddNotificationModalComponent implements OnInit {
  @Output() closeModal: EventEmitter<any> = new EventEmitter();

  courses: Performance[];

  message: string;
  selectedCourse: Performance;

  constructor(
    private notificationService: NotificationsService,
    private performanceService: PerformanceService
  ) { }

  ngOnInit(): void {
    this.fetchCourses();
  }

  fetchCourses() {
    this.performanceService
      .getPerformances()
      .subscribe((response: Performance[]) => {
        this.courses = response;
      });
  }

  pickCourse(course: Performance) {
    this.selectedCourse = course;
  }

  sendNotification() {
    this.notificationService
      .sendNotification(this.message, this.selectedCourse.id)
      .subscribe(() => {});
    this.close();
  }

  close() {
    this.closeModal.emit(null);
  }
}
