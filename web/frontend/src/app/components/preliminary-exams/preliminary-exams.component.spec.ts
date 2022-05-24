import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreliminaryExamsComponent } from './preliminary-exams.component';

describe('PreliminaryExamsComponent', () => {
  let component: PreliminaryExamsComponent;
  let fixture: ComponentFixture<PreliminaryExamsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreliminaryExamsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreliminaryExamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
