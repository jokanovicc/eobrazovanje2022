import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentCardboardComponent } from './student-cardboard.component';

describe('StudentCardboardComponent', () => {
  let component: StudentCardboardComponent;
  let fixture: ComponentFixture<StudentCardboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentCardboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentCardboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
