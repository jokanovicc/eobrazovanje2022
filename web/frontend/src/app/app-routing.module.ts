import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExamHistoryComponent } from './components/exam-history/exam-history.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { PassedExamsComponent } from './components/passed-exams/passed-exams.component';
import { RegisterExamComponent } from './components/register-exam/register-exam.component';
import { RegisteredExamsComponent } from './components/registered-exams/registered-exams.component';
import { UpdateProfileComponent } from './shared/layouts/update-profile/update-profile.component';

const routes: Routes = [
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./layouts/dashboard/dashboard.module').then(
        (m) => m.DashboardModule
      ),
  },
  {
    path: 'notifications',
    component: NotificationsComponent,
  },
  {
    path: 'exam-history',
    component: ExamHistoryComponent,
  },
  {
    path: 'passed-exams',
    component: PassedExamsComponent,
  },
  {
    path: 'registered-exams',
    component: RegisteredExamsComponent,
  },
  {
    path: 'exam-registration',
    component: RegisterExamComponent,
  },
  {
    path: '',
    loadChildren: () =>
      import('./layouts/login/login.module').then((m) => m.LoginModule),
  },

  {
    path: 'update-profile',
    component: UpdateProfileComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
