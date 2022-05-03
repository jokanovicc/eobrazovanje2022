import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PassedExamsComponent } from './passed-exams.component';

describe('PassedExamsComponent', () => {
  let component: PassedExamsComponent;
  let fixture: ComponentFixture<PassedExamsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PassedExamsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PassedExamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
