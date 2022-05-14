import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetFirstPasswordComponent } from './set-first-password.component';

describe('SetFirstPasswordComponent', () => {
  let component: SetFirstPasswordComponent;
  let fixture: ComponentFixture<SetFirstPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SetFirstPasswordComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SetFirstPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
