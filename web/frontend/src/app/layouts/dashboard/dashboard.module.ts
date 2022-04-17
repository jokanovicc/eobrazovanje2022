import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';

@NgModule({
  imports: [FormsModule, ReactiveFormsModule, DashboardRoutingModule],
  declarations: [DashboardComponent],
  exports: [DashboardComponent],
  providers: [],
})
export class DashboardModule {}
