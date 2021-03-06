import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { TeacherManagementComponent } from './components/teacher-management/teacher-management.component';
import { LoginComponent } from './components/login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor/token.interceptor';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddTeacherComponent } from './components/add-teacher/add-teacher.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AddSubjectComponent } from './components/add-subject/add-subject.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { CreateExamComponent } from './components/create-exam/create-exam.component';
import { InsertStudentsComponent } from './components/insert-students/insert-students.component';
import { SubjectsComponent } from './components/subjects/subjects.component';
import { CourseUpdateComponent } from './components/course-update/course-update.component';
import { CoursePerformanceComponent } from './components/course-performance/course-performance.component';
import { PerformanceTeacherComponent } from './components/performance-teacher/performance-teacher.component';
import { AddToPerformanceComponent } from './components/add-to-performance/add-to-performance.component';
import { DecimalPipe } from '@angular/common';
import { StudentsComponent } from './components/students/students.component';
import { StudentCardboardComponent } from './components/student-cardboard/student-cardboard.component';
import { StudentProfileComponent } from './components/student-profile/student-profile.component';
import { StudentInfoComponent } from './components/student-info/student-info.component';
import { PaymentComponent } from './components/payment/payment.component';
import { StudentPaymentsTableComponent } from './components/student-payments-table/student-payments-table.component';
import { PaymentFormComponent } from './components/payment-form/payment-form.component';
import { StudentsPerformanceComponent } from './components/students-performance/students-performance.component';

@NgModule({
  declarations: [
    AppComponent,
    TeacherManagementComponent,
    AddTeacherComponent,
    NavbarComponent,
    AddSubjectComponent,
    SidebarComponent,
    CreateExamComponent,
    InsertStudentsComponent,
    SubjectsComponent,
    CourseUpdateComponent,
    CoursePerformanceComponent,
    PerformanceTeacherComponent,
    AddToPerformanceComponent,
    StudentsComponent,
    StudentCardboardComponent,
    StudentProfileComponent,
    StudentInfoComponent,
    PaymentComponent,
    StudentPaymentsTableComponent,
    PaymentFormComponent,
    StudentsPerformanceComponent,
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
    DecimalPipe,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
