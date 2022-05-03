import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
  path: '',
  // component: LoginComponent,
  loadChildren: () =>
    import('./layouts/login/login.module').then((m) => m.LoginModule),
},{
  
  path: 'dashboard',
  // component: LoginComponent,
  loadChildren: () =>
    import('./layouts/dashboard/dashboard.module').then((m) => m.DashboardModule),
},];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
