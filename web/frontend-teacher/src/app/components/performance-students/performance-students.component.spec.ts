import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerformanceStudentsComponent } from './performance-students.component';

describe('PerformanceStudentsComponent', () => {
  let component: PerformanceStudentsComponent;
  let fixture: ComponentFixture<PerformanceStudentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PerformanceStudentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PerformanceStudentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
