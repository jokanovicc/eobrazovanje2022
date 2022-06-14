import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';

@NgModule({
  imports: [FormsModule, ReactiveFormsModule, LoginRoutingModule],
  declarations: [LoginComponent],
  exports: [LoginComponent],
  providers: [],
})
export class LoginModule {}
