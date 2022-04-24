import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';

@NgModule({
  imports: [
    FormsModule,
    ReactiveFormsModule,
    DashboardRoutingModule,
    CommonModule,
  ],
  declarations: [DashboardComponent],
  exports: [DashboardComponent],
  providers: [],
})
export class DashboardModule {}
