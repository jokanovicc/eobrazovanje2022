import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllExamsComponent } from './components/all-exams/all-exams.component';
import { AttendingsComponent } from './components/attendings/attendings.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { PerformanceStudentsComponent } from './components/performance-students/performance-students.component';
import { StudentsExamsComponent } from './components/students-exams/students-exams.component';
import { UpdateCourseComponent } from './components/update-course/update-course.component';
import { UpdateProfileComponent } from './components/update-profile/update-profile.component';


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
