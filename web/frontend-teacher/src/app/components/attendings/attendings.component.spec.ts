import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AttendingsComponent } from './attendings.component';

describe('AttendingsComponent', () => {
  let component: AttendingsComponent;
  let fixture: ComponentFixture<AttendingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AttendingsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AttendingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
