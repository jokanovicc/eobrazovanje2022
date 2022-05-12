import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { UpdateProfileComponent } from './shared/layouts/update-profile/update-profile.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { ExamHistoryComponent } from './components/exam-history/exam-history.component';
import { PassedExamsComponent } from './components/passed-exams/passed-exams.component';
import { RegisteredExamsComponent } from './components/registered-exams/registered-exams.component';
import { RegisterExamComponent } from './components/register-exam/register-exam.component';
import {
  BrowserAnimationsModule,
  NoopAnimationsModule,
} from '@angular/platform-browser/animations';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatTableModule } from '@angular/material/table';

@NgModule({
  declarations: [
    AppComponent,
    NotificationsComponent,
    NavigationBarComponent,
    UpdateProfileComponent,
    SidebarComponent,
    ExamHistoryComponent,
    PassedExamsComponent,
    RegisteredExamsComponent,
    RegisterExamComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    NoopAnimationsModule,
    BrowserAnimationsModule,
    MatTooltipModule,
    MatTableModule, // <-- Added Table Module
    MatPaginatorModule, // <-- Added Paginator Module
    MatProgressBarModule,
  ],
  exports: [FormsModule, ReactiveFormsModule],
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
