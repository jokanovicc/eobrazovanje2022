import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotificationsComponent } from './components/notifications/notifications.component';
import { DashboardComponent } from './layouts/dashboard/dashboard.component';
import { LoginComponent } from './layouts/login/login.component';
import { UpdateProfileComponent } from './shared/layouts/update-profile/update-profile.component';

const routes: Routes = [
  {
    path: 'dashboard',
    // component: DashboardComponent,
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
    path: '',
    // component: LoginComponent,
    loadChildren: () =>
      import('./layouts/login/login.module').then((m) => m.LoginModule),
  },

  {
    path: 'update-profile',
    component: UpdateProfileComponent

  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
