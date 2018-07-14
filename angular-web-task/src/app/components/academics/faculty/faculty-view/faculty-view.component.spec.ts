import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFacultyComponent } from './faculty-view.component';

describe('FacultyViewComponent', () => {
  let component: ViewFacultyComponent;
  let fixture: ComponentFixture<ViewFacultyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewFacultyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFacultyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
