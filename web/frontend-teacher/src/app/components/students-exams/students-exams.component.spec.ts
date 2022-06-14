import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsExamsComponent } from './students-exams.component';

describe('StudentsExamsComponent', () => {
  let component: StudentsExamsComponent;
  let fixture: ComponentFixture<StudentsExamsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentsExamsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentsExamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
