import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DocumentsComponent } from './components/documents/documents.component';
import { ExamHistoryComponent } from './components/exam-history/exam-history.component';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { PassedExamsComponent } from './components/passed-exams/passed-exams.component';
import { PaymentComponent } from './components/payment/payment.component';
import { PreliminaryExamsComponent } from './components/preliminary-exams/preliminary-exams.component';
import { RegisterExamComponent } from './components/register-exam/register-exam.component';
import { RegisteredExamsComponent } from './components/registered-exams/registered-exams.component';
import { SetFirstPasswordComponent } from './components/set-first-password/set-first-password.component';
import { SvFormComponent } from './components/sv-form/sv-form.component';
import { svFormGuard } from './service/svFormGuard.service';
import { UpdateProfileComponent } from './shared/layouts/update-profile/update-profile.component';

const routes: Routes = [
  {
    path: 'dashboard',
    canActivate: [svFormGuard],
    loadChildren: () =>
      import('./layouts/dashboard/dashboard.module').then(
        (m) => m.DashboardModule
      ),
  },
  {
    path: 'notifications',
    component: NotificationsComponent,
    canActivate: [svFormGuard],
  },
  {
    path: 'exam-history',
    component: ExamHistoryComponent,
    canActivate: [svFormGuard],
  },
  {
    path: 'preliminary-exams',
    component: PreliminaryExamsComponent,
    canActivate: [svFormGuard],
  },
  {
    path: 'passed-exams',
    component: PassedExamsComponent,
    canActivate: [svFormGuard],
  },
  {
    path: 'registered-exams',
    component: RegisteredExamsComponent,
    canActivate: [svFormGuard],
  },
  {
    path: 'exam-registration',
    component: RegisterExamComponent,
    canActivate: [svFormGuard],
  },
  {
    path: 'update-profile',
    component: UpdateProfileComponent,
    canActivate: [svFormGuard],
  },
  {
    path: 'setPassword',
    component: SetFirstPasswordComponent,
  },
  {
    path: 'svForm',
    component: SvFormComponent,
  },
  {
    path: 'documents',
    component: DocumentsComponent,
  },
  {
    path: 'payment',
    component: PaymentComponent,
  },
  {
    path: '**',
    loadChildren: () =>
      import('./layouts/login/login.module').then((m) => m.LoginModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
