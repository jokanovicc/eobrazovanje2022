import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddToPerformanceComponent } from './components/add-to-performance/add-to-performance.component';
import { CoursePerformanceComponent } from './components/course-performance/course-performance.component';
import { CourseUpdateComponent } from './components/course-update/course-update.component';
import { InsertStudentsComponent } from './components/insert-students/insert-students.component';
import { PaymentFormComponent } from './components/payment-form/payment-form.component';
import { PaymentComponent } from './components/payment/payment.component';
import { PerformanceTeacherComponent } from './components/performance-teacher/performance-teacher.component';
import { StudentCardboardComponent } from './components/student-cardboard/student-cardboard.component';
import { StudentProfileComponent } from './components/student-profile/student-profile.component';
import { StudentsPerformanceComponent } from './components/students-performance/students-performance.component';
import { StudentsComponent } from './components/students/students.component';
import { SubjectsComponent } from './components/subjects/subjects.component';
import { CreateExamComponent } from './components/create-exam/create-exam.component';
import { AddSubjectComponent } from './components/add-subject/add-subject.component';
import { AddTeacherComponent } from './components/add-teacher/add-teacher.component';
import { TeacherManagementComponent } from './components/teacher-management/teacher-management.component';

const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./components/login/login.module').then((m) => m.LoginModule),
  },
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./components/dashboard/dashboard.module').then(
        (m) => m.DashboardModule
      ),
  },
  {
    path: 'teachers',
    component: TeacherManagementComponent,
  },
  {
    path: 'add-teacher',
    component: AddTeacherComponent,
  },
  {
    path: 'create-exam',
    component: CreateExamComponent,
  },
  {
    path: 'add-course',
    component: AddSubjectComponent,
  },
  {
    path: 'insert-students',
    component: InsertStudentsComponent,
  },
  {
    path: 'courses',
    component: SubjectsComponent,
  },
  {
    path: 'course/:id',
    component: CourseUpdateComponent,
  },
  {
    path: 'performances',
    component: CoursePerformanceComponent,
  },
  {
    path: 'performances/:id',
    component: PerformanceTeacherComponent,
  },
  {
    path: 'add-to-performance/:id',
    component: AddToPerformanceComponent,
  },
  {
    path: 'cardboard/:id',
    component: StudentCardboardComponent,
  },
  {
    path: 'students',
    component: StudentsComponent,
  },
  {
    path: 'studentProfile/:id',
    component: StudentProfileComponent,
  },
  {
    path: 'payment/:id',
    component: PaymentComponent,
  },
  {
    path: 'addPayment/:id',
    component: PaymentFormComponent,

  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
