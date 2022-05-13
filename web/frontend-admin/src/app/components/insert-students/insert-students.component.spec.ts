import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsertStudentsComponent } from './insert-students.component';

describe('InsertStudentsComponent', () => {
  let component: InsertStudentsComponent;
  let fixture: ComponentFixture<InsertStudentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsertStudentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InsertStudentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
