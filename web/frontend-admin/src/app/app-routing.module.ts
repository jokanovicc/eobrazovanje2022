import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddSubjectComponent } from './layouts/add-subject/add-subject.component';
import { AddTeacherComponent } from './layouts/add-teacher/add-teacher.component';
import { TeacherManagementComponent } from './layouts/teacher-management/teacher-management.component';

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
},
{
  path:"teachers",
  component: TeacherManagementComponent
},{
  path:"add-teacher",
  component:AddTeacherComponent
},
{
  path:"add-course",
  component:AddSubjectComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
