import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AttendingsComponent } from './components/attendings/attendings.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { PerformanceStudentsComponent } from './components/performance-students/performance-students.component';
import { AllExamsComponent } from './layouts/all-exams/all-exams.component';
import { StudentsExamsComponent } from './layouts/students-exams/students-exams.component';
import { UpdateProfileComponent } from './layouts/update-profile/update-profile.component';
import { UpdateCourseComponent } from './update-course/update-course.component';

const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./layouts/login/login.module').then((m) => m.LoginModule),
  },
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./layouts/dashboard/dashboard.module').then(
        (m) => m.DashboardModule
      ),
  },
  {
    path: 'update-profile',
    component: UpdateProfileComponent,
  },
  {
    path: 'notifications',
    component: NotificationsComponent,
  },
  {
    path: 'exams',
    component: AllExamsComponent,
  },
  {
    path: 'student-exam/:id',
    component: StudentsExamsComponent,
  },
  {
    path: 'performances',
    component: AttendingsComponent,
  },
  {
    path: 'course/:id',
    component: UpdateCourseComponent,
  },
  {
    path: 'performances/:id/attendings',
    component: PerformanceStudentsComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
