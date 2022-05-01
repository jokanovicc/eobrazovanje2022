import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UpdateProfileComponent } from './layouts/update-profile/update-profile.component';

const routes: Routes = [  {
  path: '',
  // component: LoginComponent,
  loadChildren: () =>
    import('./layouts/login/login.module').then((m) => m.LoginModule),
},
{
  path: 'dashboard',
  // component: DashboardComponent,
  loadChildren: () =>
    import('./layouts/dashboard/dashboard.module').then(
      (m) => m.DashboardModule
    ),
},
{
  path: 'update-profile',
  component: UpdateProfileComponent

},];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
