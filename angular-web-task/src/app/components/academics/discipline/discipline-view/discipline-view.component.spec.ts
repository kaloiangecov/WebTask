import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisciplineViewComponent } from './discipline-view.component';

describe('DisciplineViewComponent', () => {
  let component: DisciplineViewComponent;
  let fixture: ComponentFixture<DisciplineViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisciplineViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisciplineViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
