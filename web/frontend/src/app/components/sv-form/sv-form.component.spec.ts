import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SvFormComponent } from './sv-form.component';

describe('SvFormComponent', () => {
  let component: SvFormComponent;
  let fixture: ComponentFixture<SvFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SvFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SvFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
