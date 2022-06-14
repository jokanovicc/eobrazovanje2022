import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { AddNotificationModalComponent } from './components/add-notification-modal/add-notification-modal.component';
import { StudentExamComponent } from './components/student-exam/student-exam.component';
import { AttendingsComponent } from './components/attendings/attendings.component';
import { PerformanceStudentsComponent } from './components/performance-students/performance-students.component';
import { AllExamsComponent } from './components/all-exams/all-exams.component';
import { StudentsExamsComponent } from './components/students-exams/students-exams.component';
import { UpdateCourseComponent } from './components/update-course/update-course.component';
import { UpdateProfileComponent } from './components/update-profile/update-profile.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationBarComponent,
    UpdateProfileComponent,
    AllExamsComponent,
    StudentsExamsComponent,
    SidebarComponent,
    NotificationsComponent,
    AddNotificationModalComponent,
    StudentExamComponent,
    AttendingsComponent,
    UpdateCourseComponent,
    PerformanceStudentsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
