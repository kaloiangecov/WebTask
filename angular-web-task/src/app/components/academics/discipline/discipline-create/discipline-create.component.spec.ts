import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisciplineCreateComponent } from './discipline-create.component';

describe('DisciplineCreateComponent', () => {
  let component: DisciplineCreateComponent;
  let fixture: ComponentFixture<DisciplineCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisciplineCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisciplineCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
