import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './layouts/login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './layouts/dashboard/dashboard.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { UpdateProfileComponent } from './layouts/update-profile/update-profile.component';
import { AllExamsComponent } from './layouts/all-exams/all-exams.component';
import { StudentsExamsComponent } from './layouts/students-exams/students-exams.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { AddNotificationModalComponent } from './components/add-notification-modal/add-notification-modal.component';

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
