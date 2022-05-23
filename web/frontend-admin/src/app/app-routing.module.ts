import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddToPerformanceComponent } from './components/add-to-performance/add-to-performance.component';
import { CoursePerformanceComponent } from './components/course-performance/course-performance.component';
import { CourseUpdateComponent } from './components/course-update/course-update.component';
import { InsertStudentsComponent } from './components/insert-students/insert-students.component';
import { PerformanceTeacherComponent } from './components/performance-teacher/performance-teacher.component';
import { StudentCardboardComponent } from './components/student-cardboard/student-cardboard.component';
import { StudentsComponent } from './components/students/students.component';
import { SubjectsComponent } from './components/subjects/subjects.component';
import { UploadFileComponent } from './components/upload-file/upload-file.component';
import { CreateExamComponent } from './create-exam/create-exam.component';
import { AddSubjectComponent } from './layouts/add-subject/add-subject.component';
import { AddTeacherComponent } from './layouts/add-teacher/add-teacher.component';
import { TeacherManagementComponent } from './layouts/teacher-management/teacher-management.component';

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
    path:'courses',
    component:SubjectsComponent
  },
  {
    path:'course/:id',
    component: CourseUpdateComponent
  },
  {
    path:'performances',
    component: CoursePerformanceComponent
  },
  {
    path:'performances/:id',
    component: PerformanceTeacherComponent
  },
  {
    path:'add-to-performance/:id',
    component: AddToPerformanceComponent
  },
  {
    path:'cardboard/:id',
    component: StudentCardboardComponent
  },
  {
    path:'students',
    component:StudentsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
