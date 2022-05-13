import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DashboardComponent } from './layouts/dashboard/dashboard.component';
import { TeacherManagementComponent } from './layouts/teacher-management/teacher-management.component';
import { LoginComponent } from './layouts/login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor/token.interceptor';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddTeacherComponent } from './layouts/add-teacher/add-teacher.component';
import { Router } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AddSubjectComponent } from './layouts/add-subject/add-subject.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { CreateExamComponent } from './create-exam/create-exam.component';
import { InsertStudentsComponent } from './components/insert-students/insert-students.component';

@NgModule({
  declarations: [
    AppComponent,
    TeacherManagementComponent,
    AddTeacherComponent,
    NavbarComponent,
    AddSubjectComponent,
    SidebarComponent,
    CreateExamComponent,
    InsertStudentsComponent
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
  providers: [    {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true,
  },],
  bootstrap: [AppComponent]
})
export class AppModule { }
